package br.ufs.dcomp.gpes.peticwizard.rest;

import com.google.api.client.protobuf.ProtocolBuffers;
import com.google.protobuf.Message;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Implementa em suas duas classes internas o recebimento e envio de mensagens
 * do Protocol Buffers (objetos que estendem {@link Message}) através de
 * webservices. Elas são utilizadas pela implementação JAX-RS para tratar
 * requisições e respostas neste formato.
 * 
 * @see Message
 * @see Provider
 * @see <a
 *      href="http://www.javarants.com/2008/12/27/using-jax-rs-with-protocol-buffers-for-high-performance-rest-apis/">Using
 *      JAX-RS with Protocol Buffers for high-performance REST APIs</a>
 */

public class ProtocolBuffersProviders {

	/**
	 * Implementação de um Entity {@link Provider} para tratar o recebimento de
	 * mensagens do Protocol Buffers (objetos que estendem {@link Message})
	 * através de webservices. É utilizada pela implementação JAX-RS para
	 * interpretar requisições recebidas do cliente neste formato, que dispara
	 * uma {@link WebApplicationException} caso não seja possível gerar através
	 * da requisição do cliente uma mensagem do Protocol Buffers.
	 * 
	 * @see Message
	 * @see Provider
	 * @see WebApplicationException
	 * @see <a
	 *      href="http://www.javarants.com/2008/12/27/using-jax-rs-with-protocol-buffers-for-high-performance-rest-apis/">Using
	 *      JAX-RS with Protocol Buffers for high-performance REST APIs</a>
	 */

	@Provider
	@Consumes(ProtocolBuffers.CONTENT_TYPE)
	public static class ProtobufMessageBodyReader implements
			MessageBodyReader<Message> {
		public boolean isReadable(Class<?> type, Type genericType,
				Annotation[] annotations, MediaType mediaType) {
			return Message.class.isAssignableFrom(type);
		}

		public Message readFrom(Class<Message> type, Type genericType,
				Annotation[] annotations, MediaType mediaType,
				MultivaluedMap<String, String> httpHeaders,
				InputStream entityStream) throws IOException,
				WebApplicationException {
			try {
				return ProtocolBuffers.parseAndClose(entityStream, type);
			} catch (Exception e) {
				throw new WebApplicationException(e);
			}
		}
	}

	/**
	 * Implementação de um Entity {@link Provider} para tratar o envio de
	 * mensagens do Protocol Buffers (objetos que estendem {@link Message})
	 * através de webservices. É utilizada pela implementação JAX-RS para gerar
	 * respostas neste formato para serem enviadas ao cliente, que dispara uma
	 * {@link WebApplicationException} caso não seja possível gerar através de
	 * uma mensagem do Protocol Buffers uma resposta ao cliente.
	 * 
	 * @see Message
	 * @see Provider
	 * @see WebApplicationException
	 * @see <a
	 *      href="http://www.javarants.com/2008/12/27/using-jax-rs-with-protocol-buffers-for-high-performance-rest-apis/">Using
	 *      JAX-RS with Protocol Buffers for high-performance REST APIs</a>
	 */

	@Provider
	@Produces(ProtocolBuffers.CONTENT_TYPE)
	public static class ProtobufMessageBodyWriter implements
			MessageBodyWriter<Message> {
		public boolean isWriteable(Class<?> type, Type genericType,
				Annotation[] annotations, MediaType mediaType) {
			return Message.class.isAssignableFrom(type);
		}

		private Map<Object, byte[]> buffer = new WeakHashMap<Object, byte[]>();

		public long getSize(Message m, Class<?> type, Type genericType,
				Annotation[] annotations, MediaType mediaType) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				m.writeTo(baos);
			} catch (IOException e) {
				return -1;
			}
			byte[] bytes = baos.toByteArray();
			buffer.put(m, bytes);
			return bytes.length;
		}

		public void writeTo(Message m, Class<?> type, Type genericType,
				Annotation[] annotations, MediaType mediaType,
				MultivaluedMap<String, Object> httpHeaders,
				OutputStream entityStream) throws IOException,
				WebApplicationException {
			entityStream.write(buffer.remove(m));
		}
	}
}
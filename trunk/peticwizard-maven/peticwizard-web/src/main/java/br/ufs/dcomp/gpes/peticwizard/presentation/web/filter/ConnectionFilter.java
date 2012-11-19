package br.ufs.dcomp.gpes.peticwizard.presentation.web.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.transaction.UserTransaction;

/**
 * Essa classe foi desenvolvida com o objetivo de manter a transa��o ativa
 * durante todo o processamento da requisi��o (do momento em que o servidor
 * recebe a requisi��o do usu�rio at� o momento em que a resposta � devolvida),
 * evitando assim a @{link LazyInitializationException} do Hibernate.
 * 
 * @see <a href="http://uaihebert.com/?p=1367&page=5">Quatro solu��es para
 *      LazyInitializationException</a>
 */

public class ConnectionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Resource
	private UserTransaction utx;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			utx.begin();
			chain.doFilter(request, response);
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
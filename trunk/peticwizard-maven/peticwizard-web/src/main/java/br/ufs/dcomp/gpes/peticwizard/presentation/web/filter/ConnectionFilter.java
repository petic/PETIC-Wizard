package br.ufs.dcomp.gpes.peticwizard.presentation.web.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.transaction.UserTransaction;

/**
 * Essa classe foi desenvolvida com o objetivo de manter a transação ativa
 * durante todo o processamento da requisição (do momento em que o servidor
 * recebe a requisição do usuário até o momento em que a resposta é devolvida),
 * evitando assim a @{link LazyInitializationException} do Hibernate.
 * 
 * @see <a href="http://uaihebert.com/?p=1367&page=5">Quatro soluções para
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
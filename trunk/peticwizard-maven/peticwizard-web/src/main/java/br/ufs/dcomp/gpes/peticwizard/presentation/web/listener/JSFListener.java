package br.ufs.dcomp.gpes.peticwizard.presentation.web.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Essa classe define um listener JSF que imprime uma mensagem na saída padrão
 * após o início e o encerramento de cada uma das fases do ciclo de vida de uma
 * requisição JSF.
 * 
 * @see PhaseListener
 */

public class JSFListener implements PhaseListener {
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		System.out.println("Depois da fase: " + event.getPhaseId());
	}

	public void beforePhase(PhaseEvent event) {
		System.out.println("Antes da fase: " + event.getPhaseId());
	}

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
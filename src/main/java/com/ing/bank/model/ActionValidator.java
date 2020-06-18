package com.ing.bank.model;

import java.io.Serializable;

public class ActionValidator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ActionState actionState;
	public String Message;

	public ActionValidator() {
		super();
	}

	public ActionValidator(ActionState actionState, String message) {
		super();
		this.actionState = actionState;
		Message = message;
	}

	public ActionState getActionState() {
		return actionState;
	}

	public void setActionState(ActionState actionState) {
		this.actionState = actionState;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Message == null) ? 0 : Message.hashCode());
		result = prime * result + ((actionState == null) ? 0 : actionState.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionValidator other = (ActionValidator) obj;
		if (Message == null) {
			if (other.Message != null)
				return false;
		} else if (!Message.equals(other.Message))
			return false;
		if (actionState != other.actionState)
			return false;
		return true;
	}
}

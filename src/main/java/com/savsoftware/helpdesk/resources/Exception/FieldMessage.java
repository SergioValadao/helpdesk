package com.savsoftware.helpdesk.resources.Exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
		private String field;
		private String defaultMessage;
		
		public FieldMessage() {
			super();
		}

		public FieldMessage(String field, String defaultmessage) {
			super();
			this.field = field;
			this.defaultMessage = defaultmessage;
		}

		public String getField() {
			return this.field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getDefaultMessage() {
			return this.defaultMessage;
		}

		public void setMessage(String message) {
			this.defaultMessage = message;
		}
		
		
}

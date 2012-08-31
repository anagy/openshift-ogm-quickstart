/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.kitchensink.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.as.quickstarts.kitchensink.model.ContactDetails;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private MemberRegistration memberRegistration;

	private Member newMember;

	private ContactDetails contactDetails;

	@Produces
	@Named
	public Member getNewMember() {
		return newMember;
	}

	@Produces
	@Named
	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void register() throws Exception {
		memberRegistration.register( newMember );
		facesContext.addMessage(
				null,
				new FacesMessage( FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful" )
		);
		initNewMember();
	}

	@PostConstruct
	public void initNewMember() {
		newMember = new Member();
		contactDetails = new ContactDetails();
		newMember.addContactDetails( contactDetails );
	}

}

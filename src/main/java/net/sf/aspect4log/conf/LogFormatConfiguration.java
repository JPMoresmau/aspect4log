/*  This file is part of the aspect4log  project.
 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation; version 2.1
 of the License.
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU Lesser General Public License for more details.
 You should have received a copy of the GNU Lesser General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.

 Copyright 2007-2014 Semochkin Vitaliy Evgenevich aka Yilativs
  
 */
package net.sf.aspect4log.conf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import net.sf.aspect4log.text.CustomisableMessageBuilderFactory;
import net.sf.aspect4log.text.MessageBuilderFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

/**
 * This class is responsible for holding log format configuration.
 * 
 * @author yilativs
 *
 */
@XmlRootElement(name="aspect4logConfiguration")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class LogFormatConfiguration {
	
	private MessageBuilderFactory messageBuilderFactory = new CustomisableMessageBuilderFactory();

	@XmlTransient
	public void setMessageBuilderFactory(MessageBuilderFactory messageBuilderFactory) {
		this.messageBuilderFactory = messageBuilderFactory;
	}

	public MessageBuilderFactory getMessageBuilderFactory()  {
		return messageBuilderFactory;
	}

	@XmlElement
	public void setMessageBuilderFactoryConfiguration(MessageBuilderFactoryConfiguration messageBuilderFactoryConfiguration) throws InstantiationException, IllegalAccessException, InvocationTargetException, DOMException, NoSuchMethodException {
		messageBuilderFactory = messageBuilderFactoryConfiguration.getClazz().getConstructor().newInstance();
		for (Element element : messageBuilderFactoryConfiguration.getProperties()) {
			BeanUtils.setProperty(messageBuilderFactory, element.getTagName(), element.getTextContent());
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class MessageBuilderFactoryConfiguration {
		@XmlAnyElement
		private final List<Element> properties = new ArrayList<Element>();

		@XmlAttribute(name = "class")
		private Class<? extends MessageBuilderFactory> clazz;

		protected List<Element> getProperties() {
			return properties;
		}

		protected Class<? extends MessageBuilderFactory> getClazz() {
			return clazz;
		}

	}
}


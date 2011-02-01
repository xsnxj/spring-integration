/*
 * Copyright 2002-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.channel;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.integration.core.Message;
import org.springframework.integration.selector.MessageSelector;

/**
 * A channel implementation that essentially behaves like "/dev/null".
 * All receive() calls will return <em>null</em>, and all send() calls
 * will return <em>true</em> although no action is performed.
 * Note however that the invocations are logged at debug-level.
 * 
 * @author Mark Fisher
 */
public class NullChannel implements PollableChannel, BeanNameAware {

	private final Log logger = LogFactory.getLog(this.getClass());

	private volatile String beanName;


	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getName() {
		return this.beanName;
	}

	public List<Message<?>> clear() {
		return null;
	}

	public List<Message<?>> purge(MessageSelector selector) {
		return null;
	}

	public Message<?> receive() {
		if (logger.isDebugEnabled()) {
			logger.debug("receive called on null channel");
		}		
		return null;
	}

	public Message<?> receive(long timeout) {
		return this.receive();
	}

	public boolean send(Message<?> message) {
		if (logger.isDebugEnabled()) {
			logger.debug("message sent to null channel: " + message);
		}
		return true;
	}

	public boolean send(Message<?> message, long timeout) {
		return this.send(message);
	}

}
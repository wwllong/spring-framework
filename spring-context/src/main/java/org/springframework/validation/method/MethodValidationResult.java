/*
 * Copyright 2002-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.validation.method;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.Errors;

/**
 * Container for method validation results with validation errors from the
 * underlying library adapted to {@link MessageSourceResolvable}s and grouped
 * by method parameter as {@link ParameterValidationResult}. For method parameters
 * with nested validation errors, the validation result is of type
 * {@link ParameterErrors} and implements {@link Errors}.
 *
 * @author Rossen Stoyanchev
 * @since 6.1
 */
public interface MethodValidationResult {

	/**
	 * Return the target of the method invocation to which validation was applied.
	 */
	Object getTarget();

	/**
	 * Return the method to which validation was applied.
	 */
	Method getMethod();

	/**
	 * Whether the violations are for a return value.
	 * If true the violations are from validating a return value.
	 * If false the violations are from validating method arguments.
	 */
	boolean isForReturnValue();

	/**
	 * Whether the result contains any validation errors.
	 */
	default boolean hasErrors() {
		return !getAllValidationResults().isEmpty();
	}

	/**
	 * Return a single list with all errors from all validation results.
	 * @see #getAllValidationResults()
	 * @see ParameterValidationResult#getResolvableErrors()
	 */
	default List<? extends MessageSourceResolvable> getAllErrors() {
		return getAllValidationResults().stream()
				.flatMap(result -> result.getResolvableErrors().stream())
				.toList();
	}

	/**
	 * Return all validation results. This includes both method parameters with
	 * errors directly on them, and Object method parameters with nested errors
	 * on their fields and properties.
	 * @see #getValueResults()
	 * @see #getBeanResults()
	 */
	List<ParameterValidationResult> getAllValidationResults();

	/**
	 * Return only validation results for method parameters with errors directly
	 * on them. This does not include Object method parameters with nested
	 * errors on their fields and properties.
	 */
	default List<ParameterValidationResult> getValueResults() {
		return getAllValidationResults().stream()
				.filter(result -> !(result instanceof ParameterErrors))
				.toList();
	}

	/**
	 * Return only validation results for Object method parameters with nested
	 * errors on their fields and properties. This excludes method parameters
	 * with errors directly on them.
	 */
	default List<ParameterErrors> getBeanResults() {
		return getAllValidationResults().stream()
				.filter(result -> result instanceof ParameterErrors)
				.map(result -> (ParameterErrors) result)
				.toList();
	}


	/**
	 * Factory method to create a {@link MethodValidationResult} instance.
	 * @param target the target Object
	 * @param method the target method
	 * @param results method validation results, expected to be non-empty
	 * @return the created instance
	 */
	static MethodValidationResult create(Object target, Method method, List<ParameterValidationResult> results) {
		return new DefaultMethodValidationResult(target, method, results);
	}

	/**
	 * Factory method to create a {@link MethodValidationResult} instance with
	 * 0 errors, suitable to use as a constant. Getters for a target object or
	 * method are not supported.
	 */
	static MethodValidationResult emptyResult() {
		return new EmptyMethodValidationResult();
	}

}

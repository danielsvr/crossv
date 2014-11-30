package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.and;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.context;
import static org.crossv.expressions.Expressions.equal;
import static org.crossv.expressions.Expressions.instance;
import static org.crossv.expressions.Expressions.instanceOf;
import static org.crossv.expressions.Expressions.memberAccess;
import static org.crossv.expressions.Expressions.not;
import static org.crossv.expressions.Expressions.validIf;
import static org.crossv.expressions.Expressions.warnIf;
import static org.crossv.expressions.Expressions.when;
import static org.crossv.primitives.Iterables.count;
import static org.crossv.primitives.Iterables.firstOrDefault;
import static org.crossv.primitives.Iterables.where;
import static org.crossv.tests.helpers.Matchers.has;
import static org.crossv.tests.helpers.Matchers.doesntHave;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.crossv.Evaluation;
import org.crossv.EvaluationFault;
import org.crossv.EvaluationWarning;
import org.crossv.Evaluator;
import org.crossv.Validation;
import org.crossv.Validator;
import org.crossv.expressions.Expression;
import org.crossv.primitives.Predicate;
import org.crossv.tests.helpers.TrackEvaluation;
import org.crossv.tests.subjects.AnyContext;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class WhenExpressionTests {

	@Test
	public void evaluatingWhenExpression_WithoutContextAndNicknameNotNull_EvaluatesOneCrossVEvaluator()
			throws Exception {
		Expression scope;
		scope = instanceOf(instance(), Monkey.class);
		Expression validNickname;
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		Iterable<Evaluator> evaluators = e.evaluate();
		assertThat(count(evaluators), is(1));
	}

	@Test
	public void evaluatingWhenExpression_AnyContextAndNicknameNotNull_EvaluatesOneCrossVEvaluator()
			throws Exception {
		Expression scope;
		scope = and(instanceOf(instance(), Monkey.class),
				instanceOf(context(), AnyContext.class));
		Expression validNickname;
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		Iterable<Evaluator> evaluators = e.evaluate();
		assertThat(count(evaluators), is(1));
	}

	@Test
	public void evaluatingWhenExpression_ContextAndInstanceInScopeAndNicknameNotNull_EvaluatesOneCrossVEvaluator()
			throws Exception {
		Expression scope;
		scope = and(instanceOf(context(), AnyContext.class),
				instanceOf(instance(), Monkey.class));
		Expression validNickname;
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		Iterable<Evaluator> evaluators = e.evaluate();
		assertThat(count(evaluators), is(1));
	}

	@Test
	public void evaluatingWhenExpression_WithoutContextAndNicknameNotNull_FirstValidationMessageIsNicknameShouldNotBeNull()
			throws Exception {
		Expression scope;
		Expression validNickname;

		scope = instanceOf(instance(), Monkey.class);
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		Iterable<Evaluator> evaluators = e.evaluate();

		Validator validator = new Validator(evaluators);
		Validation validation = validator.validate(Monkey.class, new Monkey());

		String firstMessage = firstOrDefault(validation.getMessages());
		assertThat(firstMessage, is("nickname should not be null"));
	}

	@Test
	public void evaluatingWhenExpression_AnyContextAndNicknameNotNull_FirstValidationMessageIsNicknameShouldNotBeNull()
			throws Exception {
		Expression scope;
		Expression validNickname;

		scope = and(instanceOf(instance(), Monkey.class),
				instanceOf(context(), AnyContext.class));
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		Iterable<Evaluator> evaluators = e.evaluate();

		Validator validator = new Validator(evaluators);
		Validation validation = validator.validate(Monkey.class, new Monkey(),
				AnyContext.instance);

		String firstMessage = firstOrDefault(validation.getMessages());
		assertThat(firstMessage, is("nickname should not be null"));
	}

	@Test
	public void evaluatingWhenExpression_WithoutContextNicknameShouldNotNullAndWarnNoName_NewMonkyValidationFails()
			throws Exception {
		Expression scope;
		Expression validNickname;
		Expression warnNoName;

		scope = instanceOf(instance(), Monkey.class);
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		warnNoName = warnIf(memberAccess(instance(), "Name"),
				equal(memberAccess(instance(), "Name"), constant(null)),
				"name is not compiled");
		Expression e = when(scope, validNickname, warnNoName);
		Iterable<Evaluator> evaluators = e.evaluate();

		Validator validator = new Validator(evaluators);
		Validation validation;
		validation = validator.validate(Monkey.class, new Monkey());

		assertThat(validation.isSuccessful(), is(false));
	}

	@Test
	public void evaluatingWhenExpression_AnyContextNicknameShouldNotNullAndWarnNoName_NewMonkyValidationFails()
			throws Exception {
		Expression scope;
		Expression validNickname;
		Expression warnNoName;

		scope = and(instanceOf(instance(), Monkey.class),
				instanceOf(context(), AnyContext.class));
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		warnNoName = warnIf(memberAccess(instance(), "Name"),
				equal(memberAccess(instance(), "Name"), constant(null)),
				"name is not compiled");
		Expression e = when(scope, validNickname, warnNoName);
		Iterable<Evaluator> evaluators = e.evaluate();

		Validator validator = new Validator(evaluators);
		Validation validation;
		validation = validator.validate(Monkey.class, new Monkey(),
				AnyContext.instance);

		assertThat(validation.isSuccessful(), is(false));
	}

	@Test
	public void evaluatingWhenExpression_WithoutContextNicknameShouldNotNullAndWarnNoName_NewMonkyValidationContainsOneWarning()
			throws Exception {
		Expression scope;
		Expression validNickname;
		Expression warnNoName;

		scope = instanceOf(instance(), Monkey.class);
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		warnNoName = warnIf(memberAccess(instance(), "Name"),
				equal(memberAccess(instance(), "Name"), constant(null)),
				"name is not compiled");
		Expression e = when(scope, validNickname, warnNoName);
		Iterable<Evaluator> evaluators = e.evaluate();

		Validator validator = new Validator(evaluators);
		Validation validation;
		validation = validator.validate(Monkey.class, new Monkey());

		Iterable<Evaluation> warns = where(validation.getResults(),
				new Predicate<Evaluation>() {
					@Override
					public boolean eval(Evaluation value) {
						return value instanceof EvaluationWarning;
					}
				});
		assertThat(count(warns), is(1));
	}

	@Test
	public void evaluatingWhenExpression_AnyContextNicknameShouldNotNullAndWarnNoName_NewMonkyValidationContainsOneWarning()
			throws Exception {
		Expression scope;
		Expression validNickname;
		Expression warnNoName;

		scope = and(instanceOf(instance(), Monkey.class),
				instanceOf(context(), AnyContext.class));
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		warnNoName = warnIf(memberAccess(instance(), "Name"),
				equal(memberAccess(instance(), "Name"), constant(null)),
				"name is not compiled");
		Expression e = when(scope, validNickname, warnNoName);
		Iterable<Evaluator> evaluators = e.evaluate();

		Validator validator = new Validator(evaluators);
		Validation validation;
		validation = validator.validate(Monkey.class, new Monkey(),
				AnyContext.instance);

		Iterable<Evaluation> warns = where(validation.getResults(),
				new Predicate<Evaluation>() {
					@Override
					public boolean eval(Evaluation value) {
						return value instanceof EvaluationWarning;
					}
				});
		assertThat(count(warns), is(1));
	}

	@Test
	public void evaluatingWhenExpression_WithoutContextNicknameShouldNotNullAndWarnNoName_NewMonkyValidationContainsOneFault()
			throws Exception {
		Expression scope;
		Expression validNickname;
		Expression warnNoName;

		scope = instanceOf(instance(), Monkey.class);
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		warnNoName = warnIf(memberAccess(instance(), "Name"),
				equal(memberAccess(instance(), "Name"), constant(null)),
				"name is not compiled");
		Expression e = when(scope, validNickname, warnNoName);
		Iterable<Evaluator> evaluators = e.evaluate();

		Validator validator = new Validator(evaluators);
		Validation validation;
		validation = validator.validate(Monkey.class, new Monkey());

		Iterable<Evaluation> faults = where(validation.getResults(),
				new Predicate<Evaluation>() {
					@Override
					public boolean eval(Evaluation value) {
						return value instanceof EvaluationFault;
					}
				});
		assertThat(count(faults), is(1));
	}

	@Test
	public void evaluatingWhenExpression_AnyContextNicknameShouldNotNullAndWarnNoName_NewMonkyValidationContainsOneFault()
			throws Exception {
		Expression scope;
		Expression validNickname;
		Expression warnNoName;

		scope = and(instanceOf(instance(), Monkey.class),
				instanceOf(context(), AnyContext.class));
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		warnNoName = warnIf(memberAccess(instance(), "Name"),
				equal(memberAccess(instance(), "Name"), constant(null)),
				"name is not compiled");
		Expression e = when(scope, validNickname, warnNoName);
		Iterable<Evaluator> evaluators = e.evaluate();

		Validator validator = new Validator(evaluators);
		Validation validation;
		validation = validator.validate(Monkey.class, new Monkey(),
				AnyContext.instance);

		Iterable<Evaluation> faults = where(validation.getResults(),
				new Predicate<Evaluation>() {
					@Override
					public boolean eval(Evaluation value) {
						return value instanceof EvaluationFault;
					}
				});
		assertThat(count(faults), is(1));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_AllExpressionsAreEvaluatedBySameEvaluator()
			throws Exception {
		Expression scope;
		Expression validNickname;

		scope = instanceOf(instance(), Monkey.class);
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressionsCount = 0;
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressionsCount, is(9));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_WhenExpressionIsEvaluated()
			throws Exception {
		Expression scope;
		Expression validNickname;

		scope = instanceOf(instance(), Monkey.class);
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressions = new ArrayList<Expression>();
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressions, has(e));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_ScopeInstanceOfIsNotFullyEvaluated()
			throws Exception {
		Expression scope;
		Expression validNickname;

		scope = instanceOf(instance(), Monkey.class);
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressions = new ArrayList<Expression>();
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressions, doesntHave(scope));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_ScopeClassIsEvaluated()
			throws Exception {
		Expression scope;
		Expression validNickname;

		Expression scopeClass = constant(Monkey.class);
		scope = instanceOf(instance(), scopeClass);
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressions = new ArrayList<Expression>();
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressions, has(scopeClass));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_validNicknameScopeIsNotFullyEvaluated()
			throws Exception {
		Expression scope;
		Expression validNickname;

		scope = instanceOf(instance(), constant(Monkey.class));
		Expression validNicknameScope = memberAccess(instance(), "nickname");
		validNickname = validIf(
				validNicknameScope,
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressions = new ArrayList<Expression>();
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressions,
				doesntHave(validNicknameScope));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_validNicknameIsEvaluated()
			throws Exception {
		Expression scope;
		Expression validNickname;

		scope = instanceOf(instance(), constant(Monkey.class));
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");
		Expression e = when(scope, validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressions = new ArrayList<Expression>();
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressions, has(validNickname));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_IfFalseIsEvaluated()
			throws Exception {
		Expression scope;
		Expression validNickname;

		scope = instanceOf(instance(), constant(Monkey.class));
		Expression ifFalse = constant("nickname should not be null");
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				ifFalse);
		Expression e = when(scope, validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressions = new ArrayList<Expression>();
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressions, has(ifFalse));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_TestIsEvaluated()
			throws Exception {
		Expression validNickname;

		Expression test = not(equal(memberAccess(instance(), "nickname"),
				constant(null)));

		validNickname = validIf(memberAccess(instance(), "nickname"), test,
				"nickname should not be null");
		Expression e = when(instanceOf(instance(), constant(Monkey.class)),
				validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressions = new ArrayList<Expression>();
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressions, has(test));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_TestInstanceIsEvaluated()
			throws Exception {
		Expression validNickname;

		Expression testInstance = instance();
		validNickname = validIf(
				memberAccess(testInstance, "nickname"),
				not(equal(memberAccess(testInstance, "nickname"),
						constant(null))), "nickname should not be null");
		Expression e = when(instanceOf(testInstance, constant(Monkey.class)),
				validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressions = new ArrayList<Expression>();
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressions, has(testInstance));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_TestMemberAccessIsEvaluated()
			throws Exception {
		Expression validNickname;

		Expression memberAccess = memberAccess(instance(), "nickname");
		validNickname = validIf(memberAccess(instance(), "nickname"),
				not(equal(memberAccess, constant(null))),
				"nickname should not be null");
		Expression e = when(instanceOf(instance(), constant(Monkey.class)),
				validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressions = new ArrayList<Expression>();
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressions, has(memberAccess));
	}

	@Test
	public void evaluatingWhenExpression_NicknameShouldNotBeNull_TestEqualIsEvaluated()
			throws Exception {
		Expression validNickname;

		Expression testEqual = equal(memberAccess(instance(), "nickname"),
				constant(null));
		Expression test = not(testEqual);

		validNickname = validIf(memberAccess(instance(), "nickname"), test,
				"nickname should not be null");
		Expression e = when(instanceOf(instance(), constant(Monkey.class)),
				validNickname);
		TrackEvaluation traker = new TrackEvaluation();
		TrackEvaluation.evaluatedExpressions = new ArrayList<Expression>();
		Iterable<Evaluator> evaluators = e.evaluateWith(traker);

		Validator validator = new Validator(evaluators);
		validator.validate(Monkey.class, new Monkey());

		assertThat(TrackEvaluation.evaluatedExpressions, has(testEqual));
	}

	@Test
	public void callingWhenExpressionToString_WithoutContextNicknameShouldNotNullAndWarnNoName() {
		Expression scope;
		Expression validNickname;
		Expression warnNoName;
		Expression e;

		scope = instanceOf(instance(), Monkey.class);
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");

		warnNoName = warnIf(memberAccess(instance(), "Name"),
				equal(memberAccess(instance(), "Name"), constant(null)),
				"name is not compiled");
		e = when(scope, validNickname, warnNoName);
		assertThat(
				e.toString(),
				is("when obj instanceof org.crossv.tests.subjects.Monkey ["
						+ "obj.nickname validif !(obj.nickname == null) else \"nickname should not be null\","
						+ "obj.Name warnif obj.Name == null then \"name is not compiled\""
						+ "]"));
	}

	@Test
	public void callingWhenExpressionToString_AnyContextNicknameShouldNotNullAndWarnNoName() {
		Expression scope;
		Expression validNickname;
		Expression warnNoName;
		Expression e;

		scope = and(instanceOf(instance(), Monkey.class),
				instanceOf(context(), AnyContext.class));
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");

		warnNoName = warnIf(memberAccess(instance(), "Name"),
				equal(memberAccess(instance(), "Name"), constant(null)),
				"name is not compiled");
		e = when(scope, validNickname, warnNoName);
		assertThat(
				e.toString(),
				is("when obj instanceof org.crossv.tests.subjects.Monkey "
						+ "&& context instanceof org.crossv.tests.subjects.AnyContext ["
						+ "obj.nickname validif !(obj.nickname == null) else \"nickname should not be null\","
						+ "obj.Name warnif obj.Name == null then \"name is not compiled\""
						+ "]"));
	}

	@Test
	public void callingWhenExpressionToString_WithoutContextNicknameShouldNotNull() {
		Expression scope;
		Expression validNickname;
		Expression e;

		scope = instanceOf(instance(), Monkey.class);
		validNickname = validIf(
				memberAccess(instance(), "nickname"),
				not(equal(memberAccess(instance(), "nickname"), constant(null))),
				"nickname should not be null");

		e = when(scope, validNickname);
		assertThat(
				e.toString(),
				is("when obj instanceof org.crossv.tests.subjects.Monkey ["
						+ "obj.nickname validif !(obj.nickname == null) else \"nickname should not be null\""
						+ "]"));
	}
}

package org.crossv;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.crossv.primitives.ArgumentException;
import org.crossv.primitives.Iterables;


/**
 * An registry of evaluators that will be used by the {@link Validator}.
 * 
 * @author yochanan.miykael
 */
public final class EvaluatorRegistry {
	private List<Evaluator> allEvaluators;
	private Dictionary<Class<?>, List<Evaluator>> noContextEvaluatorsByEvaluatedClass;
	private Dictionary<Class<?>, Dictionary<Class<?>, List<Evaluator>>> contextTable;

	/**
	 * Creates an instance of an {@link EvaluatorRegistry}.
	 */
	public EvaluatorRegistry() {
		this(null);
	}

	/**
	 * Creates an instance of an {@link EvaluatorRegistry}.
	 * 
	 * @param evaluator
	 *            that will be added to the registry.
	 */
	public EvaluatorRegistry(Evaluator evaluator) {
		this(evaluator, (Evaluator[]) null);
	}

	/**
	 * Creates an instance of an {@link EvaluatorRegistry}.
	 * 
	 * @param evaluators
	 *            that will be added to the registry.
	 */
	public EvaluatorRegistry(Evaluator evaluator1, Evaluator... evaluators) {
		allEvaluators = new ArrayList<Evaluator>();
		contextTable = new Hashtable<Class<?>, Dictionary<Class<?>, List<Evaluator>>>();
		noContextEvaluatorsByEvaluatedClass = new Hashtable<Class<?>, List<Evaluator>>();

		if (evaluator1 != null)
			register(evaluator1);

		if (evaluators == null)
			return;
		for (Evaluator evaluator : evaluators)
			register(evaluator);
	}

	/**
	 * Registers an instance of {@link Evaluator}.
	 * 
	 * @param evaluator
	 *            that will be registered.
	 */
	public void register(Evaluator evaluator) {
		Class<?> contextClass = evaluator.getContextClass();
		if (contextClass.equals(Object.class))
			throw new ArgumentException("evaluator",
					"An evaluator cannot have Object class as a context. "
							+ "You can provide the NoContext class or "
							+ "simply 'null' instead.");
		contextClass = contextClass != null ? contextClass : NoContext.class;
		List<Evaluator> evals;
		Class<?> instanceClass = evaluator.getInstanceClass();

		if (!contextClass.equals(NoContext.class)) {
			Dictionary<Class<?>, List<Evaluator>> entry;

			entry = contextTable.get(contextClass);
			entry = entry != null ? entry
					: new Hashtable<Class<?>, List<Evaluator>>();

			contextTable.put(contextClass, entry);

			evals = entry.get(instanceClass);
			evals = evals != null ? evals : new ArrayList<Evaluator>();

			entry.put(instanceClass, evals);

			if (!evals.contains(evaluator))
				evals.add(evaluator);
		}

		if (contextClass.equals(NoContext.class)
				|| contextClass.equals(Object.class)
				|| contextClass.getSuperclass().equals(Object.class)) {

			evals = noContextEvaluatorsByEvaluatedClass.get(instanceClass);
			evals = evals != null ? evals : new ArrayList<Evaluator>();
			noContextEvaluatorsByEvaluatedClass.put(instanceClass, evals);

			if (!evals.contains(evaluator))
				evals.add(evaluator);
		}

		allEvaluators.add(evaluator);
	}

	/**
	 * Registers an instance of {@link ContextEvaluator}&lt;E, EContext&gt;.
	 * 
	 * @param evaluator
	 *            that will be registered.
	 */
	public <E, EContext> void register(ContextEvaluator<E, EContext> evaluator) {
		register((Evaluator) evaluator);
	}

	/**
	 * Registers an instance of {@link BasicEvaluator}&lt;E&gt;.
	 * 
	 * @param evaluator
	 *            that will be registered.
	 */
	public <E> void register(BasicEvaluator<E> evaluator) {
		register((Evaluator) evaluator);
	}

	/**
	 * Evaluates if the provided evaluator is present in the registry.
	 * 
	 * @param evaluator
	 *            that will be searched.
	 * @return {@code true} is the provided evaluator is present in the
	 *         registry.{@code false} otherwise.
	 */
	public boolean contains(Evaluator evaluator) {
		return allEvaluators.contains(evaluator);
	}

	/**
	 * Gets all the registered evaluators for the provided object and context
	 * {@link Class}es.
	 * 
	 * @param objClass
	 *            is the {@link Class} of the object that will be evaluated.
	 * @param contextClass
	 *            is the {@link Class} of the context on which the object will
	 *            be evaluated.
	 * @return a sequence of evaluators for the provided object and context
	 *         {@link Class}es.
	 */
	public <E, EContext> Iterable<Evaluator> get(Class<E> objClass,
			Class<EContext> contextClass) {

		List<Evaluator> result = new ArrayList<Evaluator>();
		Class<?> actualContextClass;
		Dictionary<Class<?>, List<Evaluator>> entry;
		List<Evaluator> all;

		actualContextClass = contextClass != null ? contextClass
				: NoContext.class;

		if (!actualContextClass.equals(NoContext.class))
			do {
				entry = contextTable.get(actualContextClass);
				all = entry != null ? entry.get(objClass) : null;

				Iterables.addAllToList(result, all);

				actualContextClass = actualContextClass.getSuperclass();
			} while (actualContextClass != null);
		else {
			result = noContextEvaluatorsByEvaluatedClass.get(objClass);
		}

		return Iterables.emptyIfNull(result);
	}
}

package ie.cit.adf.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TracingAspect {

	@Before("repoOperations() && target(repo)")
	public void trace(JoinPoint jp, Object repo) {
		String className = repo.getClass().getName();
		String methodName = jp.getSignature().getName();
		System.out.println("method invoked:" + className + "#" + methodName);
	}

	//Trace all the executions of repository invocation method.
	@Pointcut("execution(* (ie.cit.adf.dao..*).*(..))")
	public void repoOperations() {

	}
}


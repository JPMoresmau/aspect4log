# Aspect4Log
Aspect4Log is a library that provides declarative logging via [slf4j](http://www.slf4j.org) and [AOP](https://en.wikipedia.org/wiki/Aspect-oriented_programming) 

It allows to replace boilerplate logging code by annotating classes and methods.

As a result you will have less code and above all your log will have a unified easy to read and analyze format.
	
By default the log output will looks  similar to the example below:

```
21:24:16 INFO  r.Robot : ↓start()
21:24:16 DEBUG r.Robot : ↓	readConfiguration(/path/to/configuration)
21:24:16 DEBUG r.Robot : ↑	readConfiguration(/path/to/configuration) → Configuration(ttl=4000,numbersOfCpuToUse=1)
21:24:16 DEBUG r.Robot : ↓	launch(Configuration(ttl=4000,numbersOfCpuToUse=1))
21:24:16 DEBUG r.Robot : ↑	launch(Configuration(ttl=4000,numbersOfCpuToUse=1)) → true
21:24:16 INFO  r.Robot : ↑start()
```

	
Do you remember how many times when something went wrong on production server you asked yourself, why didn't I (or that guy) logged it?!!

Without aspect oriented programming (AOP) it was always a challenge to reach a compromise between writing a clean code and writing a code that logs enough.

With AOP steadily getting popular by means of [SpringAOP](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html) and [aspectj](https://eclipse.org/aspectj) frameworks it is finally possible to do both.
	
Aspect4log allows you to describe what you want to log 	by  placing `@Log` annotation on a class, method or constructor.

Within `@Log` annotation you declaratively control method's enter and exit log levels, different log levels for different exceptions, arguments and returned value printing template, you can even bind [MDC](http://logback.qos.ch/manual/mdc.html) key/values.
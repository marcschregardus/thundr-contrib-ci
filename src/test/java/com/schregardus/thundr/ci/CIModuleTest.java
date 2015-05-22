package com.schregardus.thundr.ci;

import com.threewks.thundr.handlebars.HandlebarsModule;
import com.threewks.thundr.injection.UpdatableInjectionContext;
import com.threewks.thundr.module.DependencyRegistry;
import com.threewks.thundr.route.Router;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class CIModuleTest {

	@Mock private DependencyRegistry dependencyRegistry;
	@Mock private UpdatableInjectionContext injectionContext;
	@Mock private Router router;

	private CIModule ciModule;

	@Before
	public void setUp() throws Exception {

		ciModule = new CIModule();
	}

	@Test
	public void shouldSetupRequires() throws Exception {

		ciModule.requires(dependencyRegistry);

		verify(dependencyRegistry, times(1)).addDependency(HandlebarsModule.class);

		verifyNoMoreInteractions(dependencyRegistry);
	}

	@Test
	public void shouldSetupStart() throws Exception {

		when(injectionContext.get(Router.class)).thenReturn(router);

		ciModule.start(injectionContext);

		verify(injectionContext, times(1)).get(Router.class);

		verifyNoMoreInteractions(injectionContext);
	}
}
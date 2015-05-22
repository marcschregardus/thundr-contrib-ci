package com.schregardus.thundr.ci;

import com.schregardus.thundr.ci.route.ApplicationRoutes;
import com.threewks.thundr.handlebars.HandlebarsModule;
import com.threewks.thundr.injection.BaseModule;
import com.threewks.thundr.injection.UpdatableInjectionContext;
import com.threewks.thundr.module.DependencyRegistry;
import com.threewks.thundr.route.Router;

/**
 * Main entry point for the CI module - sets up the routes
 */
public class CIModule extends BaseModule {

	@Override
	public void requires(DependencyRegistry dependencyRegistry) {
		super.requires(dependencyRegistry);
		dependencyRegistry.addDependency(HandlebarsModule.class);
	}

	@Override
	public void start(UpdatableInjectionContext injectionContext) {
		super.start(injectionContext);

		Router router = injectionContext.get(Router.class);
		ApplicationRoutes.addRoutes(router);
	}
}

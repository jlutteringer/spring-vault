package org.vault.core.managed.module.registry;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vault.base.collections.tree.Tree;
import org.vault.base.collections.tree.Trees;
import org.vault.core.module.domain.Module;
import org.vault.core.module.domain.ModuleHierarchy;
import org.vault.core.module.domain.ModuleType;
import org.vault.core.module.domain.simple.ApplicationModule;
import org.vault.core.module.domain.simple.SimpleModuleHierarchy;
import org.vault.core.module.service.ModuleLoader;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Service
public class CoreModuleLoader implements ModuleLoader {

	@Autowired
	private CoreModule coreModule;

	@Autowired(required = false)
	private ApplicationModule applicationModule;

	private List<Module> modules;

	@Autowired
	private void setModules(List<Module> modules) {
		List<Module> filteredModules = Lists.newArrayList();
		for (Module module : modules) {
			if (ModuleType.MODULE.equals(module.getType())) {
				filteredModules.add(module);
			}
		}

		this.modules = filteredModules;
	}

	@PostConstruct
	private void initialize() {
		if (applicationModule == null) {
			applicationModule = new ApplicationModule();
		}
	}

	public ModuleHierarchy getModuleHierarchy() {
		ModuleHierarchy heirarchy = new SimpleModuleHierarchy();
		heirarchy.setModules(this.buildModuleTree());
		return heirarchy;
	}

	private Tree<Module> buildModuleTree() {
		Tree<Module> moduleTree = Trees.<Module> newHashTree(coreModule);
		while (!moduleTree.containsAll(modules)) {
			for (Module module : modules) {
				Set<Module> dependencies = this.getDependencies(module);
				if (moduleTree.containsAll(dependencies)) {
					for (Module dependency : dependencies) {
						moduleTree.findSubTree(dependency).add(module);
					}
				}
			}
		}

		return moduleTree;
	}

	private Set<Module> getDependencies(Module module) {
		Set<Module> dependencies = Sets.newHashSet();
		Set<Module> implicitDependencies = Sets.<Module> newHashSet(coreModule);
		for (Module dependency : module.getDependencies()) {
			implicitDependencies.addAll(getFullDependencyHierarchy(dependency));
		}

		for (Module dependency : module.getDependencies()) {
			if (!implicitDependencies.contains(dependency)) {
				dependencies.add(dependency);
			}
		}

		if (dependencies.isEmpty()) {
			dependencies.add(coreModule);
		}

		return dependencies;
	}

	private Set<Module> getFullDependencyHierarchy(Module module) {
		Set<Module> dependencies = Sets.newHashSet();
		for (Module dependency : module.getDependencies()) {
			dependencies.add(dependency);
			dependencies.addAll(this.getFullDependencyHierarchy(dependency));
		}
		return dependencies;
	}
}
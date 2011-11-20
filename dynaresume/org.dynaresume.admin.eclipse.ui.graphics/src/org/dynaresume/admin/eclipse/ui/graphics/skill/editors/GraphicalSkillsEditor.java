package org.dynaresume.admin.eclipse.ui.graphics.skill.editors;

import java.util.Collection;

import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.model.TreeDiagram;
import org.dynaresume.admin.eclipse.ui.graphics.skill.editors.parts.TreeNodeEditPartFactory;
import org.dynaresume.domain.hr.Skill;
import org.dynaresume.services.SkillService;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

public class GraphicalSkillsEditor extends GraphicalEditorWithFlyoutPalette {

	public static final String ID = "org.dynaresume.admin.eclipse.ui.graphics.skill.editors.GraphicalSkillsEditor";

	private static PaletteRoot PALETTE_MODEL;

	private SkillService skillService;

	private TreeDiagram diagram;

	public void setSkillService(SkillService skillService) {
		this.skillService = skillService;
	}

	public GraphicalSkillsEditor() {
		super.setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		if (PALETTE_MODEL == null)
			PALETTE_MODEL = SkillsEditorPaletteFactory.createPalette();
		return PALETTE_MODEL;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {

	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();

		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(TreeNodeEditPartFactory.INSTANCE);

	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();

		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(getDiagram()); // set the contents of this
		// editor
	}

	protected TreeDiagram getDiagram() {
		return diagram;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		Collection<Skill> allSkills = skillService.findAll();
		diagram = new TreeDiagram(allSkills);
	}

}
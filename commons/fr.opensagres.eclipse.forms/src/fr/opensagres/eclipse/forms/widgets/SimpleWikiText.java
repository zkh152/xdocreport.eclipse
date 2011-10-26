package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.FormToolkit;

import fr.opensagres.eclipse.forms.internal.ImageResources;

public class SimpleWikiText extends BaseComposite {

	private Text text;
	private ToolBar toolBar;
	private ToolItem boldButton;
	private ToolItem italicButton;
	
	public SimpleWikiText(Composite parent, int compositeStyle, int textStyle,
			FormToolkit toolkit) {
		super(parent, compositeStyle, toolkit);
		this.setLayout(new GridLayout());
		this.toolBar = createToolBar(this);
		text = createTextarea(this, textStyle);
	}

	private ToolBar createToolBar(Composite parent) {
	    toolBar = new ToolBar(parent, SWT.BORDER);
	    GridData gd_toolBar = new GridData(GridData.FILL_HORIZONTAL);
	    gd_toolBar.horizontalAlignment = SWT.FILL;
	    gd_toolBar.grabExcessVerticalSpace = false;
	    toolBar.setLayoutData(gd_toolBar);
	    SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	        setStyle(event.widget);
	      }
	    };
	    boldButton = new ToolItem(toolBar, SWT.PUSH);
	    boldButton.setImage(ImageResources.getImage( "icons/obj16/b_bold.gif"));
	    //boldButton.setText("B");
	    //boldButton.setImage(images.Bold);
	    boldButton.setToolTipText("Bold");
	    boldButton.addSelectionListener(listener);
	    italicButton = new ToolItem(toolBar, SWT.PUSH);
	    italicButton.setImage(ImageResources.getImage( "icons/obj16/b_italic.gif"));
	    //italicButton.setText("I");
	    //italicButton.setImage(images.Italic);
	    italicButton.setToolTipText("Italic");
	    italicButton.addSelectionListener(listener);
	    return toolBar;
	}

	private void setStyle(Widget widget) {
		//String selected = text.getSelectionText();
		Point selection=text.getSelection();
		if(boldButton.equals(widget))
		//if not empty
		if(selection.x!=selection.y){
			//find for "selected" and put <b>selected</b> around
			
			String fullText=text.getText();
			StringBuffer buffer= new StringBuffer(fullText);
			
			//first insert the ending tag "</b>"
			buffer.insert(selection.y, "</b>");
			//then insert the beginning tag
			buffer.insert(selection.x, "<b>");
			text.setText(buffer.toString());
		}

		
		
		
		
	}
	
	private Text createTextarea(Composite parent, int style) {
		Text text =  super.createText(parent, style | SWT.MULTI | SWT.WRAP);
		GridData spec = new GridData();
	    spec.horizontalAlignment = GridData.FILL;
	    spec.grabExcessHorizontalSpace = true;
	    spec.verticalAlignment = GridData.FILL;
	    spec.grabExcessVerticalSpace = true;
	    text.setLayoutData(spec);
		return text;
	}
	
	public Text getTextarea() {
		return text;
	}
}

package Cytoscape.plugin.DKernel.internal;
/**
 * @author Haotian Bai
 * @Institute CS school,Shanghai University
 */
import Cytoscape.plugin.DKernel.internal.UI.ControlPanel;
import Cytoscape.plugin.DKernel.internal.util.InputAndServices;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CytoPanelComponent;
import org.cytoscape.model.events.NetworkAboutToBeDestroyedListener;
import org.cytoscape.model.events.NetworkAddedListener;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.service.util.CyServiceRegistrar;
import org.osgi.framework.BundleContext;

import java.awt.event.ActionEvent;
import java.util.Properties;

// Building a CyActivator for OSGi
public class CyActivator extends AbstractCyActivator {
    public CyActivator() {
        super();
    }


    public void start(BundleContext bc) {
        InputAndServices.registrar = getService(bc, CyServiceRegistrar.class);
        InputAndServices.initServices();
        // Add panel
        ControlPanel cyMainPanel = new ControlPanel();
        // set control panel
        AbstractCyAction loadControlPanelAction = new AbstractCyAction("Load Dkernel") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                registerService(bc, cyMainPanel, CytoPanelComponent.class, new Properties());
            }
        };
        loadControlPanelAction.setPreferredMenu("Apps");
        // register
        registerService(bc, loadControlPanelAction, CyAction.class, new Properties());
        registerService(bc, cyMainPanel, NetworkAddedListener.class, new Properties());
        registerService(bc, cyMainPanel, NetworkAboutToBeDestroyedListener.class, new Properties());
    }

}


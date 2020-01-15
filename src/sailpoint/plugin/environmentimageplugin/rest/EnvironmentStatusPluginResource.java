package sailpoint.plugin.environmentimageplugin.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sailpoint.object.Custom;
import sailpoint.rest.plugin.AllowAll;
import sailpoint.rest.plugin.BasePluginResource;
import sailpoint.rest.plugin.RequiredRight;
import sailpoint.tools.GeneralException;
import sailpoint.tools.Util;

@RequiredRight(value = "environmentStatusPluginRESTAllow")
@Path("environmentStatusPlugin")
public class EnvironmentStatusPluginResource extends BasePluginResource {
	public static final Log log = LogFactory.getLog(EnvironmentStatusPluginResource.class);
	
	public final static String SETTING_STATUS = "environmentStatus";
	public final static String SYSTEM_CONFIG_SETTING_STATUS = "X-Plugin-Environment-Status";
	
	public final static String CUSTOM_OBJECT_NAME = "Environment Status Image Mapping";
	
	public final static String DEFAULT_IMAGE = "/ui/images/rightlogo.png";

	private String getStatusImage(String status) throws GeneralException {
		log.error(String.format("Enter: getStatusImage(%s)", status));
		if (Util.isNotNullOrEmpty(status)) {
			Custom custom = this.getContext().getObjectByName(Custom.class, CUSTOM_OBJECT_NAME);
			if (custom != null) {
				String url = custom.getString(status);
				if (Util.isNotNullOrEmpty(url)) {
					return url;
				} else {
					log.error("Could not map status to image");
				}
			} else {
				log.error("Custom object " + CUSTOM_OBJECT_NAME + " not found");
			}
		}
		return DEFAULT_IMAGE;
	}
	
	/**
	 * Return the location of the image that represents the status of the environment.
	 * 
	 * @return
	 */
	@GET
	@Path("status")
	@Produces(MediaType.APPLICATION_JSON)
	@AllowAll
	public String getStatus() throws GeneralException {
		log.debug("GET getStatus");
		String status = getSettingString(SETTING_STATUS);
		log.error(String.format("Status setting: %s", status));
		if (Util.isNullOrEmpty(status)) {
			status = getContext().getConfiguration().getString(SYSTEM_CONFIG_SETTING_STATUS);
			log.error(String.format("Status configuration: %s", status));
		}
		String url = getStatusImage(status);
		return url;
	}

	@Override
	public String getPluginName() {
		return "environmentStatusPlugin";
	}
}

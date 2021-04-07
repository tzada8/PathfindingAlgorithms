package settingsSections;

import javax.swing.JPanel;

public class SizesAndLocation extends JPanel {

    /**
     * Default Serialization
     */
    private static final long serialVersionUID = 1L;

    // Fields
    private SettingsTextField widthEntry = new SettingsTextField("Width");
    private SettingsTextField heightEntry = new SettingsTextField("Height");
    private SettingsTextField startPosEntry = new SettingsTextField("Start Point");
    private SettingsTextField endPosEntry = new SettingsTextField("End Point");

    // Panel with a section for all info regarding sizes/location of obstacles
    public SizesAndLocation() {
	this.setBackground(null);

	// Label for this frame section
	this.add(new SectionHeader("Sizes And Location").getHeader());

	this.add(heightEntry);
	this.add(widthEntry);
	this.add(startPosEntry);
	this.add(endPosEntry);
    }

    // Instance method that returns the value the user set for the WIDTH field
    public String getWidthField() {
	return widthEntry.getEntryTextField();
    }

    // Instance method that returns the value the user set for the HEIGHT field
    public String getHeightField() {
	return heightEntry.getEntryTextField();
    }

    // Instance method that returns the value the user set for the WIDTH field
    public String getStartPosField() {
	return endPosEntry.getEntryTextField();
    }

    // Instance method that returns the value the user set for the WIDTH field
    public String getEndPosField() {
	return startPosEntry.getEntryTextField();
    }

}


public class Medicine {
    private String medName;
    private String brandName;
    private int tablets;
    private int dosage;
    private int refills;
    private String acquired;

    public Medicine(String medName, String brandName, int tablets, int dosage, int refills, String acquired) {
        this.medName = medName;
        this.brandName = brandName;
        this.tablets = tablets;
        this.dosage = dosage;
        this.refills = refills;
        this.acquired = acquired;
    }

    public String getMedName() {
        return this.medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getTablets() {
        return this.tablets;
    }

    public void setTablets(int tablets) {
        this.tablets = tablets;
    }

    public int getDosage() {
        return this.dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getRefills() {
        return this.refills;
    }

    public void setRefills(int refills) {
        this.refills = refills;
    }

    public String getAcquired() {
        return this.acquired;
    }

    public void setAcquired(String acquired) {
        this.acquired = acquired;
    }

    public String toString() {
        return "Medicine:\nmedName = " + this.medName + "\nbrandName = " + this.brandName + "\ntablets = " + this.tablets + "\ndosage = " + this.dosage + "\nrefills = " + this.refills + "\nacquired = " + this.acquired + "\n";
    }
}
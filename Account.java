abstract class Account {
    private boolean isOrganization;
    private boolean isFamily;
    private final double ORGANIZATION_DISCOUNT = 0.2;

    public Account(boolean isOrganization, boolean isFamily) {
        this.isOrganization = isOrganization;
        this.isFamily = isFamily;
    }

    public Account() {
        isOrganization = false;
        isFamily = false;
    }

    abstract boolean validate(Vehicle[] vehicles);
} 
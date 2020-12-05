class Demo {

    public static void main(String[] args) {
        Phone phones[] = new Phone[10];
        String models[] = { "maximumeffort", "iflasdeluxe", "iiamaniflas" };

        int i = 0;
        for (PhoneFactory.Region region : PhoneFactory.Region.values()) {
            AbstractPhoneFactory phoneFactory = new PhoneFactory(region);
            for (String model : models) {
                phones[i] = phoneFactory.createPhone(model);
                phones[i].prepare();
                i++;
            }
        }

    }
}

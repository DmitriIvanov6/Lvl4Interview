package Task1;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    private Person(PersonBuilder personBuilder) {
        firstName = personBuilder.firstName;
        lastName = personBuilder.lastName;
        middleName = personBuilder.middleName;
        country = personBuilder.country;
        address = personBuilder.address;
        phone = personBuilder.phone;
        age = personBuilder.age;
        gender = personBuilder.gender;

    }

    public static class PersonBuilder {
        private String firstName;
        private String lastName;
        private String middleName;
        private String country;
        private String address;
        private String phone;
        private int age;
        private String gender;

        public PersonBuilder addFirstName (String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder addLastName (String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder addMiddleName (String middleName) {
            this.middleName = middleName;
            return this;
        }

        public PersonBuilder addCountry (String country) {
            this.country = country;
            return this;
        }

        public PersonBuilder addAddress (String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder addPhone (String phone) {
            this.phone = phone;
            return this;
        }

        public PersonBuilder addAge (int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder addGender (String gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}

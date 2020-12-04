public class Main {
    public static void main(String[] args) {
        Person[] persons = new Person[5];
        persons[0] = new Person("Petrov Vladimir", "Director", "director@office.com", "89215441213", 4000, 41);
        persons[1] = new Person("Golubeva Veronika", "Secretary", "secretary@office.com", "89215441224", 2500, 25);
        persons[2] = new Person("Khromov Vitaliy", "Chief Engineer", "сhief-уngineer@office.com", "89215441213", 3000, 34);
        persons[3] = new Person("Babkina Alla", "Accountant", "buhgalter@office.com", "89215496754", 3500, 45);
        persons[4] = new Person("Bystrov Sergey", "Manager", "manager@office.com", "89215448888", 2800, 43);

        for (Person person : persons) {
            if (person.getAge() > 40) person.print();
        }
    }
}

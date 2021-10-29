import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Runner {


    public static void main(String[] args) {
        List<Employee> team = DataLoader.loadData();

        //1. Вернуть фамилии всех сеньеров
        List<String> seniors = team.stream()
                .filter(employee -> employee.getTitle().equals(Employee.Title.SENIOR_ENGINEER))
                .map(Employee::getSurname)
                .collect(Collectors.toList());
        seniors.forEach(System.out::println);

        //2. Найти количество лидов в команде
        long count = team.stream()
                .filter(employee -> employee.getTitle().equals(Employee.Title.LEAD_ENGINEER))
                .count();
        System.out.println("Counter leads = " + count);


        //3. Вернуть все скиллы в команде
        List<String> skils = team.stream()
                .flatMap(employee -> employee.getSkills().stream().map(Skills::getSkill))
                .collect(Collectors.toList());
        skils.forEach(System.out::println);

        //4. Подсчитать среднее сеньорити в команде (джун - 1, миддл - 2, сеньор - 3, лид - 4)

        team.stream()
                .map(employee -> {
                    switch (employee.getTitle()) {
                        case JUNIOR_ENGINEER:
                            return 1;
                        case ENGINEER:
                            return 2;
                        case SENIOR_ENGINEER:
                            return 3;
                        case LEAD_ENGINEER:
                            return 4;
                    }
                })
                .collect(Collectors.toList());


        //5. Вернуть фамилии тех, кто знает джаву

        List<String> surnamesJavaKnolage = team.stream()
                .filter(employee -> employee.getSkills().contains(Skills.JAVA))
                .map(employee -> employee.getSurname())
                .collect(Collectors.toList());
        surnamesJavaKnolage.forEach(System.out::println);


        //6. Вернуть фамилию и тайтл тех, кто знает .Net
        List<String> surnamesAndTitlesDotNet = team.stream()
                .filter(employee -> employee.getSkills().contains(Skills.DOT_NET))
                .map(employee -> employee.getSurname() + " " + employee.getTitle())
                .collect(Collectors.toList());
        surnamesAndTitlesDotNet.forEach(System.out::println);
    }
}

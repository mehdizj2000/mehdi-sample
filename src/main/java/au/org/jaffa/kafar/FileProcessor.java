package au.org.jaffa.kafar;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import au.org.jaffa.kafar.domain.Dish;


@EnableJpaRepositories
public class FileProcessor {

    public Set<Dish> getVegDishs(List<Dish> menu) {

	return menu.stream().filter(Dish::isVegetarian).collect(Collectors.toSet());
    }

    public Set<Dish> getNoneVegDishs(List<Dish> menu) {

	Predicate<Dish> predicate = Dish::isVegetarian;
	return menu.stream().filter(predicate.negate()).collect(Collectors.toSet());
    }

}

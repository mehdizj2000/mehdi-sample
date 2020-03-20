package au.org.jaffa.kafar;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import au.org.jaffa.kafar.domain.Dish;
import au.org.jaffa.kafar.domain.Type;

public class FileProcessorTest {

    private static List<Dish> menuList;

    @BeforeEach
    public void setup() {
	menuList = Arrays.asList(new Dish("pork", false, 800, Type.MEAT), new Dish("beef", false, 700, Type.MEAT),
		new Dish("chicken", false, 400, Type.MEAT), new Dish("french fries", true, 530, Type.OTHER),
		new Dish("rice", true, 350, Type.OTHER), new Dish("season fruit", true, 120, Type.OTHER),
		new Dish("pizza", true, 550, Type.OTHER), new Dish("prawns", false, 300, Type.FISH),
		new Dish("salmon", false, 450, Type.FISH));
    }

    @Test
    public void testGetInfo() {

	FileProcessor fileProcessor = new FileProcessor();
	Set<Dish> dishs = fileProcessor.getVegDishs(menuList);
	assertNotNull(dishs);
    }

}

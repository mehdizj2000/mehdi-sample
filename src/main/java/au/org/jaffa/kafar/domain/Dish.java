package au.org.jaffa.kafar.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(of = "name")
@EqualsAndHashCode
public class Dish {
	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;
}
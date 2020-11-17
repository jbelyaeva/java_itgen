package core.general;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.testng.collections.Sets;

public class Assertions {

  public static void twoMas(String[] masOld, String[] masNew) {
    assertThat(SetUtils.equals(Sets.newHashSet(masOld), Sets.newHashSet(masNew)), is(true));
  }

}

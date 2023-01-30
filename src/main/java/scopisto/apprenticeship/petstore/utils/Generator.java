package scopisto.apprenticeship.petstore.utils;

import scopisto.apprenticeship.petstore.dataholder.Names;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Generator {
    Random random;

    public Generator() {
        random = new Random();
    }

    public String generateEmail(String name, String lastName) {
        List<String> emailExtensions = Names.emailExtensions;
        return String.format("%s%s%d%s",
                name.toLowerCase(Locale.ROOT),
                lastName.toLowerCase(Locale.ROOT),
                random.nextInt(100),
                emailExtensions.get(random.nextInt(emailExtensions.size()))
        );
    }

}

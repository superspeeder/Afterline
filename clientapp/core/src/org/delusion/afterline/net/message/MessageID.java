package org.delusion.afterline.net.message;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface MessageID {
    int id();
}

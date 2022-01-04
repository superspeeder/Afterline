package org.delusion.afterline.net.message;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SubscribeMessage {
    int id() default -1;
}

package pixart.homework
import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Example {
    var age: Int? by weak(10)
    override fun toString() = "Example Class"

    fun <T> weak (obj: T? = null) = WeakRef(obj)
}


class WeakRef<T>(obj: T? = null): ReadWriteProperty<Any?, T?> {

    private var wref : WeakReference<T>?

    init {
        this.wref = obj?.let { WeakReference(it) }
    }

    override fun getValue(thisRef:Any? , property: KProperty<*>): T? {
        return wref?.get()
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        wref = value?.let { WeakReference(it) }
    }
}

fun main() {
    val e = Example()
    println(e.age)
    e.age = 10
}



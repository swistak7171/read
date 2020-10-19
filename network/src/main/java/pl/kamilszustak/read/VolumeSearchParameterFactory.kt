package pl.kamilszustak.read

import pl.kamilszustak.model.common.VolumeSearchParameterType
import javax.inject.Inject

class VolumeSearchParameterFactory @Inject constructor() {
    fun create(parameters: Map<VolumeSearchParameterType, String>): String =
        buildString {
            var isFirst = true
            val generalValue = parameters[VolumeSearchParameterType.GENERAL]
            if (generalValue != null) {
                isFirst = false
                append(generalValue)
            }


            parameters.forEach { entry ->
                if (entry.key == VolumeSearchParameterType.GENERAL) {
                    return@forEach
                }

                if (!isFirst) {
                    append('+')
                }

                append(entry.key.queryName)
                append(':')
                append(entry.value)
            }
        }
}
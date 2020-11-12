package pl.kamilszustak.read.model.mapper.text

import com.google.mlkit.vision.text.Text
import pl.kamilszustak.read.model.domain.text.*
import pl.kamilszustak.read.model.mapper.Mapper
import javax.inject.Inject

class TextMapper @Inject constructor() : Mapper<Text, TextWrapper>() {
    override fun map(model: Text): TextWrapper {
        val blocks = mutableListOf<TextBlock>()
        model.textBlocks.forEach blocksForEach@{ textBlock ->
            val blockBox = textBlock?.boundingBox
            val blockPoints = textBlock?.cornerPoints
            if (textBlock == null || blockBox == null || blockPoints == null) {
                return@blocksForEach
            }

            val lines = mutableListOf<TextLine>()
            textBlock.lines.forEach linesForEach@{ textLine ->
                val lineBox = textLine?.boundingBox
                val linePoints = textLine?.cornerPoints
                if (textLine == null || lineBox == null || linePoints == null) {
                    return@linesForEach
                }

                val elements = mutableListOf<TextElement>()
                textLine.elements.forEach elementsForEach@{ textElement ->
                    val elementBox = textElement?.boundingBox
                    val elementPoints = textElement?.cornerPoints

                    if (textElement == null || elementBox == null || elementPoints == null) {
                        return@elementsForEach
                    }

                    val element = TextElement(
                        language = textElement.recognizedLanguage,
                        value = textElement.text,
                        boundingBox = elementBox,
                        cornerPoints = elementPoints
                    )

                    elements.add(element)
                }

                val line = TextLine(
                    language = textLine.recognizedLanguage,
                    value = textLine.text,
                    boundingBox = lineBox,
                    cornerPoints = linePoints,
                    components = elements
                )

                lines.add(line)
            }

            val block = TextBlock(
                language = textBlock.recognizedLanguage,
                value = textBlock.text,
                boundingBox = blockBox,
                cornerPoints = blockPoints,
                components = lines
            )

            blocks.add(block)
        }

        return TextWrapper(blocks)
    }
}
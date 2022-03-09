package com.dnkilic.uicomponents.images

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Images.Club: ImageVector
    get() {
        if (_clubSmall != null) {
            return _clubSmall!!
        }
        _clubSmall = Builder(name = "ClubSmall", defaultWidth = 26.0.dp, defaultHeight = 26.0.dp,
                viewportWidth = 26.0f, viewportHeight = 26.0f).apply {
            path(fill = SolidColor(Color(0xFF2C2C2C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(18.1343f, 10.3284f)
                lineTo(17.7546f, 11.7536f)
                lineTo(19.226f, 11.8534f)
                curveTo(21.9902f, 12.0409f, 24.1751f, 14.344f, 24.1751f, 17.1564f)
                curveTo(24.1751f, 20.0921f, 21.7953f, 22.4719f, 18.8597f, 22.4719f)
                curveTo(16.7675f, 22.4719f, 14.9554f, 21.2633f, 14.0875f, 19.5007f)
                lineTo(12.9975f, 17.2871f)
                lineTo(11.9075f, 19.5007f)
                curveTo(11.0397f, 21.2633f, 9.2275f, 22.4719f, 7.1354f, 22.4719f)
                curveTo(4.1997f, 22.4719f, 1.8199f, 20.0921f, 1.8199f, 17.1564f)
                curveTo(1.8199f, 14.344f, 4.0049f, 12.0409f, 6.769f, 11.8534f)
                lineTo(8.2405f, 11.7536f)
                lineTo(7.8608f, 10.3284f)
                curveTo(7.7445f, 9.8918f, 7.6821f, 9.4319f, 7.6821f, 8.9556f)
                curveTo(7.6821f, 6.0199f, 10.0619f, 3.6401f, 12.9975f, 3.6401f)
                curveTo(15.9331f, 3.6401f, 18.3129f, 6.0199f, 18.3129f, 8.9556f)
                curveTo(18.3129f, 9.4319f, 18.2506f, 9.8918f, 18.1343f, 10.3284f)
                close()
            }
        }
        .build()
        return _clubSmall!!
    }

private var _clubSmall: ImageVector? = null

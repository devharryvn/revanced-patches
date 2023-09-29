package app.revanced.patches.youtube.utils.annotations

import app.revanced.patcher.annotation.Compatibility
import app.revanced.patcher.annotation.Package

@Compatibility(
    [
        Package(
            "com.google.android.youtube",
            arrayOf(
                "18.22.37",
                "18.23.36",
                "18.24.37",
                "18.25.40",
                "18.27.36",
                "18.29.38",
                "18.30.37",
                "18.31.40",
                "18.32.39"
            )
        )
    ]
)
@Target(AnnotationTarget.CLASS)
internal annotation class YouTubeCompatibility


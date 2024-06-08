package app.revanced.patches.youtube.layout.actionbuttons

import app.revanced.patcher.data.ResourceContext
import app.revanced.patcher.patch.PatchException
import app.revanced.patcher.patch.options.PatchOption.PatchExtensions.stringPatchOption
import app.revanced.patches.youtube.utils.compatibility.Constants.COMPATIBLE_PACKAGE
import app.revanced.patches.youtube.utils.settings.SettingsPatch
import app.revanced.util.ResourceGroup
import app.revanced.util.copyResources
import app.revanced.util.patch.BaseResourcePatch

@Suppress("unused")
object ShortsActionButtonsPatch : BaseResourcePatch(
    name = "Custom Shorts action buttons",
    description = "Changes, at compile time, the icon of the action buttons of the Shorts player.",
    dependencies = setOf(SettingsPatch::class),
    compatiblePackages = COMPATIBLE_PACKAGE
) {
    private const val DEFAULT_ICON_KEY = "Round"

    private val IconType by stringPatchOption(
        key = "IconType",
        default = DEFAULT_ICON_KEY,
        values = mapOf(
            "Outline" to "outline",
            "OutlineCircle" to "outlinecircle",
            DEFAULT_ICON_KEY to "round"
        ),
        title = "Shorts icon style ",
        description = "The style of the icons for the action buttons in the Shorts player."
    )

    override fun execute(context: ResourceContext) {
        IconType?.let { iconType ->
            val selectedIconType = iconType.lowercase()

            arrayOf(
                "xxxhdpi",
                "xxhdpi",
                "xhdpi",
                "hdpi",
                "mdpi"
            ).forEach { dpi ->
                context.copyResources(
                    "youtube/shorts/actionbuttons/$selectedIconType",
                    ResourceGroup(
                        "drawable-$dpi",
                        "ic_remix_filled_white_shadowed.webp",
                        "ic_right_comment_shadowed.webp",
                        "ic_right_dislike_off_shadowed.webp",
                        "ic_right_dislike_on_shadowed.webp",
                        "ic_right_like_off_shadowed.webp",
                        "ic_right_like_on_shadowed.webp",
                        "ic_right_share_shadowed.webp",

                        // for older versions only
                        "ic_remix_filled_white_24.webp",
                        "ic_right_dislike_on_32c.webp",
                        "ic_right_like_on_32c.webp"
                    ),
                    ResourceGroup(
                        "drawable",
                        "ic_right_comment_32c.xml",
                        "ic_right_dislike_off_32c.xml",
                        "ic_right_like_off_32c.xml",
                        "ic_right_share_32c.xml"
                    )
                )

                context.copyResources(
                    "youtube/shorts/actionbuttons/shared",
                    ResourceGroup(
                        "drawable",
                        "reel_camera_bold_24dp.xml",
                        "reel_more_vertical_bold_24dp.xml",
                        "reel_search_bold_24dp.xml"
                    )
                )
            }
        } ?: throw PatchException("Invalid icon type.")

        SettingsPatch.updatePatchStatus(this)
    }
}

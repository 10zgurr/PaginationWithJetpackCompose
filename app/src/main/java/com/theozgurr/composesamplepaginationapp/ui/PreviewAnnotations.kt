package com.theozgurr.composesamplepaginationapp.ui

import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "phone",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480",
    showBackground = true
)
annotation class PreviewPhone

@Preview(
    name = "tablet",
    device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480",
    showBackground = true
)
annotation class PreviewTablet

@PreviewPhone
@PreviewTablet
annotation class PreviewAllScreens

@Preview(showBackground = true)
annotation class PreviewWithBackground
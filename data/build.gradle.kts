import DependencyHandler.Extensions.implementations

plugins {
    id(ConventionEnum.AndroidLibrary)
    id(ConventionEnum.AndroidHilt)
    id(ConventionEnum.JvmJUnit4)
}

android {
    namespace = "team.brave.devevent.android.data"
}

dependencies {
    implementations(
        libs.kotlin.coroutines,
        projects.domain,
    )
}

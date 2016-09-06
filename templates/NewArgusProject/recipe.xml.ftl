<?xml version="1.0"?>
<recipe>

    <instantiate from="project/build.gradle.ftl"
                 to="${escapeXmlAttribute(topOut)}/build.gradle" />
    <copy from="project/project_ignore"
          to="${escapeXmlAttribute(topOut)}/.gitignore" />
    <instantiate from="project/settings.gradle.ftl"
                 to="${escapeXmlAttribute(topOut)}/settings.gradle" />

    <instantiate from="project/gradle.properties.ftl"
                 to="${escapeXmlAttribute(topOut)}/gradle.properties" />

    <copy from="../gradle/wrapper"
          to="${escapeXmlAttribute(topOut)}/" />

    <instantiate from="project/local.properties.ftl"
                 to="${escapeXmlAttribute(topOut)}/local.properties" />

    <dependency mavenUrl="com.android.support:appcompat-v7:${buildApi}.+"/>

    <mkdir at="${escapeXmlAttribute(srcOut)}" />
    <mkdir at="${escapeXmlAttribute(projectOut)}/libs" />
    <merge from="module/settings.gradle.ftl"
             to="${escapeXmlAttribute(topOut)}/settings.gradle" />
    <instantiate from="module/build.gradle.ftl"
                   to="${escapeXmlAttribute(projectOut)}/build.gradle" />
    <copy from="module/module_ignore"
            to="${escapeXmlAttribute(projectOut)}/.gitignore" />
    <instantiate from="module/proguard-rules.txt.ftl"
                   to="${escapeXmlAttribute(projectOut)}/proguard-rules.pro" />
</recipe>

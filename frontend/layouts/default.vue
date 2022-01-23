<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Laboratorio TBD - 1</v-toolbar-title>
            
            <v-spacer></v-spacer>

            <v-toolbar-items>
                <v-btn to="/emergencias/add">Registro de Emergencia</v-btn>
                <v-btn to="/emergencias/">Detalles emergencias</v-btn>
            </v-toolbar-items>



        </v-app-bar>

        <!-- Sizes your content based upon application components -->
        <v-main>
            <!-- Provides the application the proper gutter -->
            <v-container fluid>
                <Nuxt />
            </v-container>
        </v-main>

        <v-footer app>
            TBD
        </v-footer>
    </v-app>
</template>

<template>
    <v-card>
        <v-card-text>    
            <v-form @submit.prevent="addEmergencia"
                ref="form"
                v-model="valid"
                lazy-validation
            >
                <h1>Crear Voluntario</h1>
                <v-row class="mt-4">
                    <v-col cols="12" md="6">
                        <v-text-field filled
                            label="Nombre"
                            v-model="voluntario.name"
                            :rules="[v => !!v || 'Nombre es requerido']"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="12" md="6">
                        <v-menu
                            ref="menu"
                            v-model="menu"
                            :close-on-content-click="false"
                            :return-value.sync="voluntario.fnacimiento"
                            transition="scale-transition"
                            offset-y
                            min-width="auto"
                        >
                            <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                    v-model="voluntario.fnacimiento"
                                    label="Fecha de nacimiento"
                                    filled
                                    readonly
                                    v-bind="attrs"
                                    v-on="on"
                                    :rules="[v => !!v || 'La fecha de nacimiento es requerida']"
                                ></v-text-field>
                            </template>
                            <v-date-picker
                                v-model="voluntario.fnacimiento"
                                no-title
                                scrollable
                            >
                                <v-spacer></v-spacer>
                                <v-btn
                                    text
                                    color="primary"
                                    @click="menu = false"
                                >
                                    Cancel
                                </v-btn>
                                <v-btn
                                    text
                                    color="primary"
                                    @click="$refs.menu.save(voluntario.fnacimiento)"
                                >
                                OK
                                </v-btn>
                            </v-date-picker>
                        </v-menu>
                    </v-col>
                    
                    
                    <!-- POSTGIS -->
                    <v-col cols="12" md="6">
                        <v-text-field filled
                            label="Longitud"
                            type="number"
                            v-model="voluntario.longitude"
                            :rules="[v => !!v || 'Longitud es requerido']"
                        ></v-text-field>
                    </v-col>
                    <v-col cols="12" md="6">
                        <v-text-field filled
                            label="Latitud"
                            type="number"
                            v-model="voluntario.latitude"
                            :rules="[v => !!v || 'Latitud es requerido']"
                        ></v-text-field>
                    </v-col>
                </v-row>
                
                <v-card-actions>    
                    <v-btn
                        color="primary"
                        tile
                        class="pa-5"
                        @click="addVoluntario"
                    >
                        Crear
                    </v-btn>
                </v-card-actions>
            </v-form>
        </v-card-text>
    </v-card>
</template>

<script>
export default {
    components: {},
    props: {},
    data: () => ({
        voluntario: {},
        menu: false,
        valid: true,
    }),
    mounted() {
        
    },
    methods: {
        addVoluntario() {
            if(this.$refs.form.validate()){
                this.$axios.post('/voluntario', this.voluntario)
                    .then(response => {
                        console.log(response);
                        if(response.data.id){
                            this.$refs.form.reset();
                            this.$router.push('/voluntarios');
                        }
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }
        }
    },
}
</script>

<style>

</style>
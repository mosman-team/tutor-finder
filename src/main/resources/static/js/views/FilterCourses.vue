<template>

    <div>
        <v-row justify="center">
            <v-col cols="12" sm="10" md="8" lg="6">

                <v-form ref="form" @submit.prevent="submitHandler" v-model="valid" :lazy-validation="lazy">
                    <v-card class="pa-2">
                        <v-card-text>
                            <div>
                                <v-autocomplete
                                        v-model="city"
                                        :items="getAllCities"
                                        dense
                                        outlined
                                        label="Choose city"
                                        placeholder="Almaty"
                                        :rules="cityRules"
                                        required
                                ></v-autocomplete>
                            </div>

                            <div class="price-for-month">
                                <img src="/static/img/tenge.png" alt="" id="price-image">
                                <v-text-field
                                        v-model="price"
                                        label="Maximum amount you are able to pay"
                                        placeholder="0"
                                        required
                                        :rules="priceRules"
                                ></v-text-field>
                            </div>

                            <div>
                                <header>Язык обучения</header>
                                <v-radio-group class="ma-1"
                                               v-model="language"
                                               :rules="languageRules"
                                               required
                                >
                                    <v-radio
                                            v-for="n in languages"
                                            :key="n"
                                            :label="`${n}`"
                                            :value="n"
                                    ></v-radio>
                                </v-radio-group>
                            </div>

                            <div>
                                <span></span>

                                <v-select
                                        v-model="categories"
                                        :items="items"
                                        chips
                                        clearable
                                        label="Choose course category"
                                        multiple
                                        prepend-icon="filter_list"
                                        solo
                                >
                                    <template v-slot:selection="{ attrs, item, select, selected }">
                                        <v-chip
                                                v-bind="attrs"
                                                :input-value="selected"
                                                @click="select"
                                                @click:close="remove(item)"
                                        >
                                            <strong>{{ item }}</strong>&nbsp;
                                        </v-chip>
                                    </template>
                                </v-select>

                            </div>

                        </v-card-text>
                        <v-card-actions>
                            <v-btn color="error" text @click="resetForm">Reset Form</v-btn>
                            <v-spacer></v-spacer>
                            <v-btn color="primary" text type="submit" :disabled="!valid">Filter</v-btn>
                        </v-card-actions>

                    </v-card>
                </v-form>
            </v-col>
        </v-row>

        <v-row justify="center">
            <v-col cols="12" sm="12" md="12" lg="11">
                <div class="headline legend grey--text text--darken-1">
                    Available courses
                </div>

                <div class="courses">
                    <div v-for="(course, i) in getCourses" :key="i" class="course">
                        <student-course
                                :course="course">
                        </student-course>
                    </div>
                </div>

            </v-col>
        </v-row>
    </div>

</template>

<script>
    import {mapActions, mapGetters} from 'vuex';

    import StudentCourse from "../components/StudentCourse.vue";

    export default {
        name: "FilterCourses",
        components: {
            StudentCourse
        },

        data: function () {
            return {
                city: '',
                price: '',
                language: '',
                categories: null,
                submitted: false,

                valid: true,
                lazy: false,

                cityRules: [
                    v => !!this.getAllCities.includes(v) || 'Город должен быть выбран',
                ],
                priceRules: [
                    v => !!v || 'Цена курса должна присутствовать',
                    v => /^\d+$/.test(v) || 'Значение должно быть цифрой'
                ],
                addressRules: [
                    v => !!v || 'Адрес проведения обучения должен быть выбран'
                ],
                languageRules: [
                    v => !!this.languages.includes(v) || 'Язык обучуния должен быть выбран'
                ],
                languages: [
                    'Казахский',
                    'Английский',
                    'Русский'
                ],
                items: [
                    'Business & Management',
                    'Creative Arts & Media',
                    'Healthcare & Medicine',
                    'History',
                    'IT & Computer Science',
                    'Language',
                    'Law',
                    'Literature',
                    'Nature & Environment',
                    'Politics & Society',
                    'Psychology & Mental Health',
                    'Science, Engineering & Maths',
                    'Study Skills',
                    'Teaching'
                ],
            };
        },
        computed: {
            ...mapGetters(['getCourses', 'getAllCities']),
        },

        methods: {
            ...mapActions(['filterCoursesAndFetchAction', 'fetchAllCoursesAction']),
            resetForm() {
                this.$refs.form.reset()
            },
            submitHandler() {
                if (this.$refs.form.validate()) {
                    const dataInfo = {
                        city: this.city,
                        price: this.price,
                        language: this.language,
                        keyWords: this.categories
                    }

                    this.filterCoursesAndFetchAction(dataInfo);

                }
            },
            remove (item) {
                this.categories.splice(this.categories.indexOf(item), 1)
                this.categories = [...this.categories]
            },

        },
        created() {
            this.fetchAllCoursesAction()
        }

    }
</script>

<style scoped>
    #price-image{
        height: 20px;
    }
    .price-for-month{
        display: flex;
    }
    .legend {
        padding-bottom: 10px;
        border-bottom: 1px solid lightgrey;
    }
    .courses{
        margin-top: 50px;
    }
</style>
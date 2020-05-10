<template>
    <v-form ref="form" @submit.prevent="submitHandler" v-model="valid" :lazy-validation="lazy">
        <v-card class="pa-2">
            <v-card-text>
                <div>
                    <v-autocomplete
                            v-model="getCurrentCourse.city"
                            :items="getAllCities"
                            dense
                            outlined
                            label="Choose city"
                            placeholder="Almaty"
                            :rules="cityRules"
                            required
                    ></v-autocomplete>
                </div>

                <div>
                    <v-text-field
                            ref="title"
                            label="Address"
                            placeholder="просп. Абая 151"
                            v-model="getCurrentCourse.address"
                            required
                            :rules="addressRules"
                    >
                    </v-text-field>
                </div>

                <div class="price-for-month">
                    <img src="/static/img/tenge.png" alt="" id="price-image">
                    <v-text-field
                            v-model="getCurrentCourse.price"
                            label="Monthly Payment"
                            placeholder="0"
                            required
                            :rules="priceRules"
                    ></v-text-field>
                </div>

                <div>
                    <header>Language of education</header>
                    <v-radio-group class="ma-1"
                                   v-model="getCurrentCourse.language"
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
                    <span class="label">Course category</span>

                    <v-select
                            v-model="getCurrentCourse.keyWords"
                            :items="items"
                            chips
                            clearable
                            label="IT & computer science"
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
                <v-btn color="primary" text type="submit" :disabled="!valid">Submit</v-btn>
            </v-card-actions>

        </v-card>
    </v-form>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex';

    export default {
        name: "SetCourseInfo",
        components: {},

        data: function () {
            return {
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
        computed: mapGetters(['getCurrentCourse', 'getAllCities']),
        methods: {
            ...mapMutations(['removeFromKeyWordsMutation']),
            ...mapActions(['addInfoAction']),
            finishCreateCourse() {
                this.$router.push('/courses');
            },
            remove(item) {
                const index = this.getCurrentCourse.keyWords.indexOf(item);
                if (index > -1) {
                    this.getCurrentCourse.keyWords.splice(index, 1);
                }
            },
            resetForm() {
                this.$refs.form.reset()
            },
            submitHandler() {

                if (this.$refs.form.validate()) {

                        const data = {
                            courseId: this.getCurrentCourse.id,
                            info : {
                                city: this.getCurrentCourse.city,
                                address: this.getCurrentCourse.address,
                                price: this.getCurrentCourse.price,
                                language: this.getCurrentCourse.language,
                                keyWords: this.getCurrentCourse.keyWords
                            }
                        }
                        this.addInfoAction(data);
                }

            },
        },
    }
</script>

<style scoped>
    #price-image{
        height: 20px;
    }
    .price-for-month{
        display: flex;
    }

</style>
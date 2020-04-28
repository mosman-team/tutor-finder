<template>
    <v-container>
        <v-row justify="center">
            <v-card width="500" height="500" class="d-flex flex-column justify-content-between">
                <v-card-text>
                    <v-flex class="mb-4 d-flex justify-center">
                        <v-avatar size="96" class="mr-4">
                            <img :src="userImg" alt="Avatar">
                        </v-avatar>
                    </v-flex>

                    <v-text-field
                            v-model="currentUser.username"
                            readonly
                            label="Username"></v-text-field>
                    <v-text-field
                            readonly
                            v-model="currentUser.email"
                            label="Email Address">
                    </v-text-field>

                    <v-btn class="mt-3" width="150" @click="openFileDialog">Change Avatar</v-btn>
                    <div class="mt-4 d-flex flex-column" v-if="imgChip.display">
                        <div class="mt-3">
                            <span class="mr-2 mt-3">New Image:</span>
                            <v-chip
                                    close
                                    color="success"
                                    text-color="white"
                                    @click:close="closeChip">
                                {{imgChip.text}}
                            </v-chip>
                        </div>
                    </div>
                </v-card-text>
                <v-card-actions>
                    <v-btn color="primary" @click="saveChanges" class="mx-auto">
                        <v-icon left dark>check</v-icon>
                        Save Changes
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-row>
<!--        <input type="file" id="file-upload" style="display:none" @change="onFileChange">-->
        <v-file-input
                v-model="files"
                style="display:none"
                accept="image/*"
                id="file-upload"
                @change="onFileChange">
        </v-file-input>
    </v-container>
</template>

<script>
    import UserImgOrDefault from "../../services/helper-functions";

    export default {
        name: 'Profile',
        data () {
            return {
                imgChip : {
                    display : false,
                    text : null
                },
                files : [],
            }
        },
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },
            userImg(){
                return UserImgOrDefault(this.currentUser)
            }
        },
        mounted() {
            if (!this.currentUser) {
                this.$router.push('/login');
            }
        },
        methods : {
            openFileDialog() {
                document.getElementById('file-upload').click();
            },
            onFileChange() {
                if (this.files){
                    this.imgChip.display = true
                    this.imgChip.text = this.files.name
                }
            },
            closeChip(){
                this.imgChip.display = false
                this.imgChip.text = null
                this.files = null
            },
            saveChanges(){
                let formData = new FormData();
                formData.append("file", this.files);
                formData.append("username", this.currentUser.username)
                this.closeChip();
                this.$store.dispatch('auth/updateUserAction', {formData : formData})
            }
        }
    };
</script>

<style scoped>

</style>
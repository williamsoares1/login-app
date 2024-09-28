import axios from "axios";

export const login_api = axios.create({
    baseURL: 'http://localhost:8080',
    withCredentials: true
});
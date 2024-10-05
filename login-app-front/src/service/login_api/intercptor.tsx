import { useEffect } from "react";
import { login_api } from "./login_api";

let isRefreshing = false;
let failedRequestsQueue: { resolve: (value: unknown) => void; reject: (reason?: any) => void; }[] = [];

export const setupInterceptor = () => {
    login_api.interceptors.response.use(
        function (response) {
            
            return response;
        }, 
        async function (error) {
            const originalRequest = error.config; 

            
            if (error.response && error.response.status === 401 || error.response && error.response.status === 403 || error.response && error.response.status === 500) {
                if (!isRefreshing) {

                    isRefreshing = true;
                    try {
                        await login_api.post("/auth/refresh-token");
                        return login_api(originalRequest);
                    } catch (refreshError) {
                        return Promise.reject(error);
                    } finally {
                        isRefreshing = false;
                    }
                }
                
                return new Promise((resolve, reject) => {
                    failedRequestsQueue.push({ resolve, reject });
                });
            }
            
            return Promise.reject(error);
        }
    );

    login_api.interceptors.response.use(response => {
        if (isRefreshing) {
            failedRequestsQueue.forEach(({ resolve }) => resolve(response));
            failedRequestsQueue = [];
        }
        return response;
    });
};


export const InterceptorComp = () => {

    useEffect(() => {
        const fetchData = async () => {
            try {
                await login_api.get("/interceptor");
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
    }, []);
}
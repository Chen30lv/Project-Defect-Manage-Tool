// initialization.ts
import axios, { AxiosInstance } from 'axios';
import { GlobalState } from './globalState';
import * as vscode from 'vscode';
import { SideBarGeneric } from '../views/dashboard'




interface LoginResponse {
  // 根据你的API响应调整
  token: string;
}


  

async function postData(context: vscode.ExtensionContext) {
	try {
	  const httpClient = await httpClientPromise;
  
	  // 使用httpClient进行POST请求
	  const response = await httpClient.post('/api/defectInfo/search/MyDefectInfoVOList?userId=1', {
		// 你的POST数据
	  });
  
	  console.log('Response:', response.data);
      GlobalState.defectInfoArray = response.data.data;
      console.log(GlobalState.defectInfoArray);
      const sidebarTodo = context.globalState.get('sidebarTodo') as SideBarGeneric;
      sidebarTodo.refresh();

	} catch (error) {
	  console.error('Error during data posting:', error);
	}
  }

async function initializeHttpClient(): Promise<AxiosInstance> {
  const httpClient = axios.create({
    baseURL: 'http://134.175.54.235:8101', // 设置你的baseURL
    withCredentials: true,
  });

  // 执行登录操作
  const loginResponse = await httpClient.post<LoginResponse>('/api/user/login', {
    // 这里填入登录所需的数据，例如用户名和密码
    account: 'dy',
    password: '12345678',
  });


  // 假设登录响应中包含了token
  const token = loginResponse.data.token;
  console.log('Login Response:', loginResponse);


  
  

  return httpClient;
}

const httpClientPromise = initializeHttpClient();


export { httpClientPromise };



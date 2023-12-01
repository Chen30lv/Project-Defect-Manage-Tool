// globalState.ts

interface UserVO {
  id: number;
  account: string;
  createTime: string;
  updateTime: string;
}
  
interface Project {
  id: number;
  projectName: string;
}

interface DefectInfo {
  id: number;
  userVO: UserVO;
  project: Project;
  defectName: string;
  defectStatus: string;
  defectDetail: string;
  defectType: string;
  defectLevel: string;
  isToDo : string;
  userId: number;
  projectId: number;
  defectComment: string[];
  createTime: string;
  updateTime: string;
}

interface ApiResponse {
  code: number;
  data: DefectInfo[];
  message: string;
}


export const GlobalState = {
  defectInfoArray: [] as DefectInfo[],
};

export interface ProjectStats {
  [projectName: string]: number;
}
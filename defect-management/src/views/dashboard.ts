import * as vscode from 'vscode';
import axios, { AxiosInstance } from 'axios';
import { GlobalState } from '../utils/globalState';

function selectEmoji(Level: string): string {
  switch (Level) {
    case "Critical":
      return "🔴"; 
    case "High":
      return "🟠"; 
    case "Medium":
      return "🟡"; 
    case "Low":
      return "⚪"; 
  }
  return "⚪"; 
}

async function fetchData(context: vscode.ExtensionContext): Promise<void> {
	try {
	  const httpClient = axios.create({
      baseURL: 'http://134.175.54.235:8101', // 设置你的baseURL
      withCredentials: true,
    });
  
	  // 使用httpClient进行POST请求
	  const response = await httpClient.post('/api/defectInfo/search/MyDefectInfoVOList?userId=2', {
		// 你的POST数据
	  });
  
    console.log('Response:', response.data);
    GlobalState.defectInfoArray = response.data.data;
    console.log(GlobalState.defectInfoArray);

	} catch (error) {
	  console.error('Error during data posting:', error);
	}
}

async function modifyStatus(context: vscode.ExtensionContext, item: SideBarEntryItem, status: string): Promise<void> {
	try {
	  const httpClient = axios.create({
      baseURL: 'http://134.175.54.235:8101', // 设置你的baseURL
      withCredentials: true,
    });
  
    const data = {
      defectStatus: status,
      id: item.defectID,
      userId: item.userId
    };

	  // 使用httpClient进行POST请求
	  const response = await httpClient.post('/api/defectInfo/update', data);
  
    console.log('Response:', response.data);
    GlobalState.defectInfoArray = response.data.data;
    console.log(GlobalState.defectInfoArray);

	} catch (error) {
	  console.error('Error during data posting:', error);
	}
}


// Custom Tree Item Class
export class SideBarEntryItem extends vscode.TreeItem {
  constructor(public readonly defectName: string, public readonly defectID: number,
     public readonly userId: number,
     public readonly defectStatus: string, public readonly defectType: string, 
     public readonly defectLevel: string, public readonly defectDetail: string, 
     public readonly defectComment: string, public readonly collapsibleState: vscode.TreeItemCollapsibleState) {

    super(defectName, collapsibleState);
    

    if (this.label != "TODO" && this.label != "FINISHED" && this.label != "STATISTICS") {
      this.description = `Project ${Math.ceil(Math.random() * 1000)}`;
      this.contextValue = "editableEntry";
      
      let tooltipContent = new vscode.MarkdownString();
      tooltipContent.appendMarkdown(`**${defectName}** ${defectLevel}\n\n`);
      tooltipContent.appendMarkdown(`- **Status:** ${defectStatus}\n`);
      tooltipContent.appendMarkdown(`- **Defect Type:** ${defectType}\n`);
      tooltipContent.appendMarkdown(`---\n\n`);
      tooltipContent.appendMarkdown(`**Defect Detail:**\n${defectDetail}\n\n`);
      tooltipContent.appendMarkdown(`---\n\n`);
      tooltipContent.appendMarkdown(`**Defect Comments:**\n${defectComment}\n`);

      this.tooltip = tooltipContent;
      this.tooltip.isTrusted = true;

    }
  }
}

export class SideBarGeneric implements vscode.TreeDataProvider<SideBarEntryItem> {
  private _onDidChangeTreeData: vscode.EventEmitter<SideBarEntryItem | undefined | null | void> = new vscode.EventEmitter<SideBarEntryItem | undefined | null | void>();
  readonly onDidChangeTreeData: vscode.Event<SideBarEntryItem | undefined | null | void> = this._onDidChangeTreeData.event;
  
  constructor(private workspaceRoot?: string) {}

  refresh(): void {
    this._onDidChangeTreeData.fire();
  }

  getTreeItem(element: SideBarEntryItem): vscode.TreeItem {
    return element;
  }

  getChildren(element?: SideBarEntryItem): vscode.ProviderResult<SideBarEntryItem[]> {
    if (element) {
      // Child nodes
      var childrenList = [];
      console.log("array length:");
      console.log(GlobalState.defectInfoArray.length)
      for (let index = 0; index < GlobalState.defectInfoArray.length; index++) {
        //console.log(GlobalState.defectInfoArray[index].isToDo)
        console.log(GlobalState.defectInfoArray[index]);
        console.log(element.label)
        
        if (GlobalState.defectInfoArray[index].isToDo == element.label) {
          let emoji: string = selectEmoji(GlobalState.defectInfoArray[index].defectLevel);
          var item = new SideBarEntryItem(
            GlobalState.defectInfoArray[index].defectName,
            GlobalState.defectInfoArray[index].id,
            GlobalState.defectInfoArray[index].userId,
            GlobalState.defectInfoArray[index].defectStatus,
            GlobalState.defectInfoArray[index].defectType,
            emoji,
            GlobalState.defectInfoArray[index].defectDetail,
            GlobalState.defectInfoArray[index].defectComment,
            vscode.TreeItemCollapsibleState.None
          )
          
          childrenList[index] = item
        }
      }
      return childrenList
    } else {
      // Create root tags
      return [
        new SideBarEntryItem(
          'TODO',
          0,
          0,
          "null",
          "null",
          "null",
          "null",
          "null",
          vscode.TreeItemCollapsibleState.Expanded
        ),
        new SideBarEntryItem(
          'FINISHED',
          0,
          0,
          "null",
          "null",
          "null",
          "null",
          "null",
          vscode.TreeItemCollapsibleState.Expanded
        ),
        new SideBarEntryItem(
          'STATISTICS',
          0,
          0,
          "null",
          "null",
          "null",
          "null",
          "null",
          vscode.TreeItemCollapsibleState.Expanded
        ),
      ]
    }
  }
}

function registerDashboardCommands(context: vscode.ExtensionContext ,sidebar: SideBarGeneric) {
  context.subscriptions.push(vscode.commands.registerCommand('dashboard.markAsFixed', async (item: SideBarEntryItem)  => {
    console.log("Start posting");
    await modifyStatus(context, item, "Fixed");
    await fetchData(context);
    sidebar.refresh();
    vscode.window.showInformationMessage(`Modify status for defect: ${item.defectName}`);
  }));

  context.subscriptions.push(vscode.commands.registerCommand('dashboard.markAsDeferred', async (item) => {
    console.log("Start posting");
    await modifyStatus(context, item, "Deferred");
    await fetchData(context);
    sidebar.refresh();
    vscode.window.showInformationMessage(`Modify status for defect: ${item.defectName}`);
  }));

  context.subscriptions.push(vscode.commands.registerCommand('dashboard.markAsNotABug', async (item) => {
    console.log("Start posting");
    await modifyStatus(context, item, "NotABug");
    await fetchData(context);
    sidebar.refresh();
    vscode.window.showInformationMessage(`Modify status for defect: ${item.defectName}`);
  }));

  context.subscriptions.push(vscode.commands.registerCommand('dashboard.markAsDuplicate', async (item) => {
    console.log("Start posting");
    await modifyStatus(context, item, "Duplicate");
    await fetchData(context);
    sidebar.refresh();
    vscode.window.showInformationMessage(`Modify status for defect: ${item.defectName}`);
  }));

  context.subscriptions.push(vscode.commands.registerCommand('dashboard.edit', async (item) => {
    const userInput = await vscode.window.showInputBox({
      prompt: "Enter new data",
      placeHolder: "Type the new data here"
    });
    if (userInput) {
      vscode.window.showInformationMessage(`asdasdasd ${item.defectName}`);
    }
  
  }));
}


module.exports = async function (context: vscode.ExtensionContext) {
  // Register Sidebar Panels
  const sidebar = new SideBarGeneric();
  
  
  vscode.window.registerTreeDataProvider('dashboard', sidebar);
  
  vscode.commands.registerCommand('dashboard.refresh', async () => {
    console.log("Start posting");
    await fetchData(context);
    sidebar.refresh();
  });

  registerDashboardCommands(context, sidebar);

  console.log("Start posting");
  await fetchData(context);
  sidebar.refresh();
  
};

function varargout = aesForm(varargin)
% AESFORM MATLAB code for aesForm.fig
%      AESFORM, by itself, creates a new AESFORM or raises the existing
%      singleton*.
%
%      H = AESFORM returns the handle to a new AESFORM or the handle to
%      the existing singleton*.
%
%      AESFORM('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in AESFORM.M with the given input arguments.
%
%      AESFORM('Property','Value',...) creates a new AESFORM or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before aesForm_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to aesForm_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help aesForm

% Last Modified by GUIDE v2.5 09-Jul-2019 16:07:27

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @aesForm_OpeningFcn, ...
                   'gui_OutputFcn',  @aesForm_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before aesForm is made visible.
function aesForm_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to aesForm (see VARARGIN)

% Choose default command line output for aesForm
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% UIWAIT makes aesForm wait for user response (see UIRESUME)
% uiwait(handles.figure1);


% --- Outputs from this function are returned to the command line.
function varargout = aesForm_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;



function inputTextEdit_Callback(hObject, eventdata, handles)
% hObject    handle to inputTextEdit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of inputTextEdit as text
%        str2double(get(hObject,'String')) returns contents of inputTextEdit as a double


% --- Executes during object creation, after setting all properties.
function inputTextEdit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to inputTextEdit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in inputTextStrRadioBtn.
function inputTextStrRadioBtn_Callback(hObject, eventdata, handles)
% hObject    handle to inputTextStrRadioBtn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of inputTextStrRadioBtn


% --- Executes on button press in inputTextHexRadioBtn.
function inputTextHexRadioBtn_Callback(hObject, eventdata, handles)
% hObject    handle to inputTextHexRadioBtn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of inputTextHexRadioBtn



function inputKeyEdit_Callback(hObject, eventdata, handles)
% hObject    handle to inputKeyEdit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of inputKeyEdit as text
%        str2double(get(hObject,'String')) returns contents of inputKeyEdit as a double


% --- Executes during object creation, after setting all properties.
function inputKeyEdit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to inputKeyEdit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in inputKeyStrRadioBtn.
function inputKeyStrRadioBtn_Callback(hObject, eventdata, handles)
% hObject    handle to inputKeyStrRadioBtn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of inputKeyStrRadioBtn


% --- Executes on button press in inputKeyHexRadioBtn.
function inputKeyHexRadioBtn_Callback(hObject, eventdata, handles)
% hObject    handle to inputKeyHexRadioBtn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hint: get(hObject,'Value') returns toggle state of inputKeyHexRadioBtn


% --- Executes on button press in executeBtn.
function executeBtn_Callback(hObject, eventdata, handles)
% hObject    handle to executeBtn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
    try
        debugOutputStr = '';

        if (get(handles.inputTextStrRadioBtn, 'Value') == 1); inputTextMode = TextMode.STRING; else inputTextMode = TextMode.HEX; end;
        if (get(handles.inputKeyStrRadioBtn, 'Value') == 1); keyTextMode = TextMode.STRING; else keyTextMode = TextMode.HEX; end;
        if (get(handles.encryptRadioBtn, 'Value') == 1); cipherMode = CipherMode.ENCRYPT; else cipherMode = CipherMode.DECRYPT; end;

        inputText = get(handles.inputTextEdit, 'String');
        inputKey = get(handles.inputKeyEdit, 'String');
        
        startDateTime = datetime('now', 'Format', 'dd-MM-yyyy HH:mm:ss.SSS');

        [inputTextHex, inputTextHexDispStr] = encryptDecryptInputTextPrep(inputText, cipherMode, inputTextMode);
        [keyHex, keyHexDispStr] = keyTextPrep(inputKey, keyTextMode);
        debugOutputStr = horzcat(debugOutputStr, inputTextHexDispStr, keyHexDispStr);

        completionMessage = '';
        outputHexDisp = '';
        outputStringDisp = '';
        outputDispStr = '';

        switch cipherMode
            case CipherMode.ENCRYPT
                [outputHexDisp, outputStringDisp, outputDispStr] = encrypt(inputTextHex, keyHex);
                completionMessage = 'The PlainText has been successfully Encrypted!';
            case CipherMode.DECRYPT
                [outputHexDisp, outputStringDisp, outputDispStr] = decrypt(inputTextHex, keyHex);
                completionMessage = 'The CipherText has been successfully Decrypted!';
            otherwise
                error('An unexpected error has occured!');
        end

        if isempty(outputHexDisp) || isempty(outputStringDisp)
            error('Please enter inputs!');
        end

        endDateTime = datetime('now', 'Format', 'dd-MM-yyyy HH:mm:ss.SSS');
        td = endDateTime - startDateTime;
        td.Format = 's';
        td = char(td);
        
        debugOutputStr = horzcat(debugOutputStr, outputDispStr);
        debugOutputStr = horzcat(debugOutputStr, sprintf('\nElapsed Time: %s\n\n', td));

        set(handles.outputHexEdit, 'String', outputHexDisp);
        set(handles.outputStrEdit, 'String', outputStringDisp);
        set(handles.debugOutputEdit, 'String', debugOutputStr);
        helpdlg(completionMessage, 'Complete!');
    catch exception
        helpdlg(exception.message, 'Error!');
        %rethrow(exception);
    end


function outputHexEdit_Callback(hObject, eventdata, handles)
% hObject    handle to outputHexEdit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of outputHexEdit as text
%        str2double(get(hObject,'String')) returns contents of outputHexEdit as a double


% --- Executes during object creation, after setting all properties.
function outputHexEdit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to outputHexEdit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end



function outputStrEdit_Callback(hObject, eventdata, handles)
% hObject    handle to outputStrEdit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of outputStrEdit as text
%        str2double(get(hObject,'String')) returns contents of outputStrEdit as a double


% --- Executes during object creation, after setting all properties.
function outputStrEdit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to outputStrEdit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes on button press in outputHexCopyBtn.
function outputHexCopyBtn_Callback(hObject, eventdata, handles)
% hObject    handle to outputHexCopyBtn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

    clipboard('copy', get(handles.outputHexEdit, 'String'));
    helpdlg('Text Copied!', 'Copied!');

% --- Executes on button press in outputStrCopyBtn.
function outputStrCopyBtn_Callback(hObject, eventdata, handles)
% hObject    handle to outputStrCopyBtn (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

    clipboard('copy', get(handles.outputStrEdit, 'String'));
    helpdlg('Text Copied!', 'Copied!');

function debugOutputEdit_Callback(hObject, eventdata, handles)
% hObject    handle to debugOutputEdit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'String') returns contents of debugOutputEdit as text
%        str2double(get(hObject,'String')) returns contents of debugOutputEdit as a double


% --- Executes during object creation, after setting all properties.
function debugOutputEdit_CreateFcn(hObject, eventdata, handles)
% hObject    handle to debugOutputEdit (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: edit controls usually have a white background on Windows.
%       See ISPC and COMPUTER.
if ispc && isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor','white');
end


% --- Executes during object creation, after setting all properties.
function figure1_CreateFcn(hObject, eventdata, handles)
% hObject    handle to figure1 (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called
aesInit

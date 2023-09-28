import React from 'react';
import { Outlet, useNavigate } from "react-router-dom";
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import SpaceDashboardRoundedIcon from '@mui/icons-material/SpaceDashboardRounded';
import AddIcCallRoundedIcon from '@mui/icons-material/AddIcCallRounded';
import LeaderboardRoundedIcon from '@mui/icons-material/LeaderboardRounded';
import PermContactCalendarRoundedIcon from '@mui/icons-material/PermContactCalendarRounded';
import { Avatar, Typography } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';





const drawerWidth = 260;

const theme = createTheme({
    palette: {
        main: "#1C2536",
        light: "#6366F1",
        dark: "#bdbdbd",
        hover: "#252E3E"
    }
});


const RootLayout = () => {


    const navigate = useNavigate();

    const menuItems = [
        {
            text: "Dashboard",
            icon: <SpaceDashboardRoundedIcon />,
            path: "/dashboard"
        },
        {
            text: "Contacts",
            icon: <PermContactCalendarRoundedIcon />,
            path: "/contacts"
        },
        {
            text: "Add Phone",
            icon: <AddIcCallRoundedIcon />,
            path: "/addphone"
        },
        {
            text: "Statics",
            icon: <LeaderboardRoundedIcon />,
            path: "/statics"
        }
    ]

    return (
        <Box sx={{
            display: 'flex',
        }}>
            <ThemeProvider theme={theme}>
                <CssBaseline />
                <Drawer
                    sx={{
                        width: drawerWidth,
                        flexShrink: 0,

                        '& .MuiDrawer-paper': {
                            width: drawerWidth,
                            boxSizing: 'border-box',
                            backgroundColor: "main",
                            color: "dark"
                        },
                    }}
                    variant="permanent"
                    anchor="left"
                >
                    <Box sx={{
                        display: 'flex',
                        alignItems: 'center',
                        m: 3
                    }}>
                        <Avatar sx={{ mx: 3 }} />
                        <Typography sx={{ lineHeight: 'normal' }}>Example Ltd.</Typography>
                    </Box>
                    <Divider sx={{ backgroundColor: "dark" }} />
                    <List sx={{
                        mx: 1.5
                    }}>
                        {menuItems.map(item => (
                            <ListItem key={item.text} disablePadding onClick={() => (navigate(item.path))}>
                                <ListItemButton sx={{
                                    borderRadius: '16px',
                                    '&:hover': {
                                        backgroundColor: "hover",
                                        color: "light",
                                        '& .MuiListItemIcon-root': {
                                            color: "light",
                                        },
                                    },
                                }}  >
                                    <ListItemIcon sx={{ color: 'dark' }} >
                                        {item.icon}
                                    </ListItemIcon>
                                    <ListItemText sx={{ fontWeight: 'medium', }} primary={item.text} />
                                </ListItemButton>
                            </ListItem>
                        ))}
                    </List>
                    <Divider sx={{ backgroundColor: "dark" }} />
                </Drawer>
            </ThemeProvider>
            <Box
                component="main"
                sx={{ flexGrow: 1, bgcolor: 'background.default', p: 3 }}
            >
                <Toolbar />
                <Outlet />
            </Box>
        </Box>
    );
}

export default RootLayout





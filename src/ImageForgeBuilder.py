#! /usr/bin/python3
import bpy, os

# List of names for the rendered frames
frame_names = [
    "Coin_1", 
    "Coin_5", 
    "Coin_10",
    "Coin_25",
    "Coin_50",

    'Paper_1',
    'Paper_2',
    'Paper_5',
    'Paper_10',
    'Paper_25',
    'Paper_50',
    'Paper_100',
    ] # Replace with your actual list of names

# Get scene and render settings
scene = bpy.context.scene
render = scene.render

# Store original output settings
original_path = render.filepath


bpy.data.scenes["Scene"].render.resolution_x = 512          # Render -> Format -> Resolution X
bpy.data.scenes["Scene"].render.resolution_y = 512          # Render -> Format -> Resolution Y
bpy.data.scenes["Scene"].view_settings.view_transform = 1   # Render -> Color Management -> View Transform



# Build Coins
for frame in range(0, 4 + 1):
    # Set current frame
    scene.frame_set(frame)
    
    # Set output path for this frame
    frame_index = frame - scene.frame_start
    frame_name = frame_names[frame_index]
    render.filepath = f"//images/{frame_name}.png"
    
    # Render frame
    bpy.ops.render.render(write_still=True)


# Build Paper Bills
for frame in range(5, 5 + 7 + 1):

    bpy.data.scenes["Scene"].render.resolution_x = 1280
    bpy.data.scenes["Scene"].render.resolution_y = 512
    bpy.data.scenes["Scene"].view_settings.view_transform = 7

    # Set output path for this frame
    frame_index = frame - scene.frame_start
    frame_name = frame_names[frame_index]
    render.filepath = f"//build/images/{frame_name}.png"

    # Render frame
    bpy.ops.render.render(write_still=True)


    bpy.data.scenes["Scene"].render.resolution_x = 512*2
    bpy.data.scenes["Scene"].render.resolution_y = 512
    bpy.data.scenes["Scene"].view_settings.view_transform = 1





# Restore original render path
render.filepath = original_path